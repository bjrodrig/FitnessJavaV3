package controller;

import entity.FooddatabaseEntity;
import logic.Urls;
import session.FoodDatabaseQueries;
import Const.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FoodDatabaseServlet")
public class FoodDatabaseServlet extends HttpServlet {
    Urls urlInstance = new Urls();
    FoodDatabaseQueries foodDatabaseQueriesInstance = new FoodDatabaseQueries();
    Const constantInstance = new Const();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List foodItem = new ArrayList();
        String meal;
        List<FooddatabaseEntity> foodDatabaseItems = foodDatabaseQueriesInstance.returnAllFoodDatabaseItems();
        String isFoodItemSelected = request.getParameter("isFoodItemSelected");
        if (isFoodItemSelected.equals("false")) {
            meal = request.getParameter("meal");
        }
        else {
            meal = (String) request.getSession(false).getAttribute("meal");
        }
        String foodName = request.getParameter("foodItemIndex");
        if (isFoodItemSelected.equals("true")) {
            foodItem = foodDatabaseQueriesInstance.validateFoodName(foodName);
        }
        HttpSession session = request.getSession(false);
        String url = urlInstance.createFullUrlString("/displayFoodDatabase");
        session.setAttribute("foodDatabaseItems", foodDatabaseItems);
        session.setAttribute("foodItem", foodItem);
        session.setAttribute("isFoodItemSelected", isFoodItemSelected);
        session.setAttribute("meals", constantInstance.MEALS);
        session.setAttribute("meal", meal);
        try {
            response.sendRedirect(url);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        List<FooddatabaseEntity> foodDatabaseItems = foodDatabaseQueriesInstance.returnAllFoodDatabaseItems();
        String meal = request.getParameter("meal");
        HttpSession session = request.getSession(false);
        String url = urlInstance.createFullUrlString("/displayFoodDatabase");
        session.setAttribute("foodDatabaseItems", foodDatabaseItems);
        session.setAttribute("meal", meal);
        try {
            response.sendRedirect(url);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
