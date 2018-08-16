package controller;

import com.google.gson.JsonObject;
import logic.Urls;
import session.FoodDatabaseQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AddFoodToDatabaseServlet")
public class AddFoodToDatabaseServlet extends HttpServlet {
    Urls urlInstance = new Urls();
    FoodDatabaseQueries foodDatabaseQueriesInstance = new FoodDatabaseQueries();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String foodName = request.getParameter("foodName");
        String url;
        List existingFood = foodDatabaseQueriesInstance.validateFoodName(foodName);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        JsonObject myObj = new JsonObject();

        if (existingFood.isEmpty()) {
            myObj.addProperty("success", true);
        } else {
            myObj.addProperty("success", false);
        }

        out.println(myObj.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = urlInstance.createFullUrlString("/addFoodToDatabase");
        try {
            response.sendRedirect(url);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
