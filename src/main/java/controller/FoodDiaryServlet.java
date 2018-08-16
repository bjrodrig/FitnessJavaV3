package controller;

import com.google.gson.JsonObject;
import entity.FooddatabaseEntity;
import entity.FooddiaryEntity;
import entity.UserEntity;
import entity.UserprofileEntity;
import logic.FoodDiaryLogic;
import logic.ParseDates;
import logic.Urls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Const.Const;
import session.FoodDatabaseQueries;
import session.FooddiaryQueries;
import session.UserProfileQueriesBean;
import session.UserQueriesBean;
import sun.plugin.util.UserProfile;

import static java.lang.StrictMath.round;

@WebServlet(name = "FoodDiaryServlet")
public class FoodDiaryServlet extends HttpServlet {

    private ParseDates dates = new ParseDates();
    private Urls urlInstance = new Urls();
    private Const constant = new Const();
    private FoodDatabaseQueries foodDatabaseQueriesInstance = new FoodDatabaseQueries();
    private FooddiaryQueries fooddiaryQueriesInstance = new FooddiaryQueries();
    private FoodDiaryLogic foodDiaryLogicInstance = new FoodDiaryLogic();
    private UserQueriesBean userQueriesBeanInstance = new UserQueriesBean();
    private UserProfileQueriesBean userProfileQueriesBeanInstance = new UserProfileQueriesBean();
    private ParseDates parseDatesInstance = new ParseDates();
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String reasonForPost = request.getParameter("reasonForPost");
        if (reasonForPost == null) {
            reasonForPost = "Add Food";
        }
        Float unitsConsumed = 0.0f;
        String foodName = "";
        String meal = "";
        String todayString = "";

        HttpSession session = request.getSession(false);

        if (reasonForPost.equals("Add Food")) {
            foodName = request.getParameter("foodItem");
            unitsConsumed = Float.valueOf(request.getParameter("unitsConsumed"));
            meal = request.getParameter("meal");
            FoodDiaryLogic foodDiaryJSP = (FoodDiaryLogic) session.getAttribute("foodDiaryJSP");
            todayString = foodDiaryJSP.getTodayString();
        }
        else {
            todayString = request.getParameter("foodDiarySelectedDate");
        }

        String username = (String) session.getAttribute("username");
        UserEntity user = (UserEntity)  userQueriesBeanInstance.validateUser(username).get(0);
        Date dateAdded = dates.parseDatesLongFormat(todayString);

        if (reasonForPost.equals("Add Food")) {
            foodDiaryLogicInstance.createNewFoodDiaryEntry(foodDatabaseQueriesInstance, unitsConsumed, foodName,
                    dateAdded, user, meal);
        }

        FoodDiaryLogic foodDiaryJSP = new FoodDiaryLogic(username, todayString);

        session.setAttribute("foodDiaryJSP", foodDiaryJSP);

        String url = urlInstance.createFullUrlString("/foodDiary");
        try {
            response.sendRedirect(url);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        UserEntity user = (UserEntity) userQueriesBeanInstance.validateUser(username).get(0);

        String reasonForGet = request.getParameter("reasonForGet");
        if (reasonForGet.equals("delete")) {
            Integer id = Integer.valueOf(request.getParameter("fooddiaryId"));
            fooddiaryQueriesInstance.deleteFoodDiaryEntry(id);
        }
        else if (reasonForGet.equals("copyYesterday") || reasonForGet.equals("copyTomorrow")) {
            FoodDiaryLogic foodDiaryJSP = (FoodDiaryLogic) session.getAttribute("foodDiaryJSP");
            String dateAddedString = foodDiaryJSP.getTodayString();
            Date dateAdded = parseDatesInstance.parseDatesLongFormat(dateAddedString);
            String fromMeal = request.getParameter("fromMeal");
            String toMeal = request.getParameter("toMeal");
            if (reasonForGet.equals("copyYesterday")) {
                Date previousDay = parseDatesInstance.returnDate(dateAdded, -1);
                List<FooddiaryEntity> foodDiaryEntries = fooddiaryQueriesInstance.
                        retrieveFooddiaryItemsByUsernameDateMeal(user, previousDay, fromMeal);
                for(Iterator<entity.FooddiaryEntity> i = foodDiaryEntries.iterator(); i.hasNext();) {
                    FooddiaryEntity item = i.next();
                    /*FooddiaryEntity foodItem = new FooddiaryEntity();*/
                    fooddiaryQueriesInstance.createFoodDiaryEntry(dateAdded, toMeal,
                            item.getCaloriesPerItem(), item.getQuantity(), item.getTotalCalories(),
                            item.getFoodItem(), item.getUsername());
                };
            }
            else if (reasonForGet.equals("copyTomorrow")) {
                Date nextDate = parseDatesInstance.returnDate(dateAdded, 1);
                List<FooddiaryEntity> foodDiaryEntries = fooddiaryQueriesInstance.
                        retrieveFooddiaryItemsByUsernameDateMeal(user, dateAdded, fromMeal);
                for(Iterator<FooddiaryEntity> i = foodDiaryEntries.iterator(); i.hasNext();) {
                    FooddiaryEntity item = i.next();
                    /*FooddiaryEntity foodItem = new FooddiaryEntity();*/
                    fooddiaryQueriesInstance.createFoodDiaryEntry(nextDate, toMeal,
                            item.getCaloriesPerItem(), item.getQuantity(), item.getTotalCalories(),
                            item.getFoodItem(), item.getUsername());
                };

            }
        }
        FoodDiaryLogic foodDiaryJSP = new FoodDiaryLogic(reasonForGet, session, username);

        session.setAttribute("foodDiaryJSP", foodDiaryJSP);

        String url = urlInstance.createFullUrlString("/foodDiary");
        try {
            response.sendRedirect(url);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
