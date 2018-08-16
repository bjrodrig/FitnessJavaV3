package controller;

import entity.FooddatabaseEntity;
import logic.Urls;
import session.FoodDatabaseQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SuccessfullyAddedFoodToDatabaseServlet")
public class SuccessfullyAddedFoodToDatabaseServlet extends HttpServlet {
    Urls urlInstance = new Urls();
    FoodDatabaseQueries foodDatabaseQueriesInstance = new FoodDatabaseQueries();
    FooddatabaseEntity foodItem = new FooddatabaseEntity();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String foodName = request.getParameter("foodName");
        Float servingSize = Float.valueOf(request.getParameter("servingSize"));
        String servingUnit = request.getParameter("servingUnit");
        Float calories = Float.valueOf(request.getParameter("calories"));
        List existingFoodNames = foodDatabaseQueriesInstance.validateFoodName(foodName);
        if (!existingFoodNames.isEmpty()) {
            session.setAttribute("foodNameFormError", "Food name already exists in database.");
            String url = urlInstance.createFullUrlString("/addFoodToDatabase");
            try {
                response.sendRedirect(url);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            foodDatabaseQueriesInstance.createFoodDatabaseItem(foodName, servingSize,
                    servingUnit, calories);
            session.setAttribute("foodName", foodName);
            session.setAttribute("foodNameFormError", "");
            String url = urlInstance.createFullUrlString("/successfullyAddedFoodToDatabase");
            try {
                response.sendRedirect(url);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
