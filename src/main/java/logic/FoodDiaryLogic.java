package logic;

import entity.FooddatabaseEntity;
import entity.FooddiaryEntity;
import entity.UserEntity;

import java.util.*;

import Const.Const;
import entity.UserprofileEntity;
import session.FoodDatabaseQueries;
import session.FooddiaryQueries;
import session.UserProfileQueriesBean;
import session.UserQueriesBean;

import javax.servlet.http.HttpSession;

import static java.lang.StrictMath.round;

public class FoodDiaryLogic {
    Const constantInstance = new Const();
    ParseDates parseDatesInstance = new ParseDates();
    UserQueriesBean userQueriesBeanInstance = new UserQueriesBean();
    UserProfileQueriesBean userProfileQueriesBeanInstance = new UserProfileQueriesBean();
    FooddiaryQueries fooddiaryQueriesInstance = new FooddiaryQueries();
    List<String> meals = constantInstance.MEALS;
    Date dateAdded = new Date();
    String todayString = "";
    String username = "";
    UserEntity user;
    UserprofileEntity userprofile;
    List<FooddiaryEntity> foodDiaryItems;
    List<Float> caloriesPerMeal;
    Float totalCaloriesForDay;
    long caloriesRemaining;

    public Date getDateAdded() {
        return dateAdded;
    }

    public UserEntity getUserEntity() {
        return user;
    }

    public List<String> getMeals() {
        return meals;
    }

    public List<FooddiaryEntity> getFoodDiaryItems() {
        return foodDiaryItems;
    }

    public String getTodayString() {
        return todayString;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserprofileEntity getUserprofile() {
        return userprofile;
    }

    public List<Float> getCaloriesPerMeal() {
        return caloriesPerMeal;
    }

    public Float getTotalCaloriesForDay() {
        return totalCaloriesForDay;
    }

    public long getCaloriesRemaining() {
        return caloriesRemaining;
    }

    public FoodDiaryLogic() {}

    public FoodDiaryLogic(String reasonForGetorPost, HttpSession session, String username) {
        setTodayAndTodayString(reasonForGetorPost, session);
        setUserInformation(username);
        setFoodDiaryInformation();
        meals = constantInstance.MEALS;
    }

    public FoodDiaryLogic(String username, String todayString) {
        this.todayString = todayString;
        this.dateAdded = parseDatesInstance.parseDatesLongFormat(todayString);
        setUserInformation(username);
        setFoodDiaryInformation();
    }

    public void setUserInformation(String username) {
        this.username = username;
        this.user = (UserEntity)  userQueriesBeanInstance.validateUser(username).get(0);
        this.userprofile = (UserprofileEntity)  userProfileQueriesBeanInstance
                .retrieveUserProfileFromUsername(this.user);
    }

    public void setFoodDiaryInformation() {
        this.foodDiaryItems = fooddiaryQueriesInstance.retrieveFooddiaryItemsByUsernameAndDate(
                user, dateAdded);
        this.caloriesPerMeal = this.returnListOfCaloriesPerMeal();
        this.totalCaloriesForDay = fooddiaryQueriesInstance.retrieveSumOfCaloriesByUsernameAndDate(user, dateAdded);
        this.caloriesRemaining = round((this.userprofile.getCalorieGoalPerDay() - this.totalCaloriesForDay) * 100) /
                100;
    }

    public void setTodayAndTodayString(String reasonForGetorPost, HttpSession session) {
        if (reasonForGetorPost.equals("FromHomePage")) {
            this.dateAdded = parseDatesInstance.returnToday();
            this.todayString = parseDatesInstance.convertDateToString(dateAdded);
        } else {
            FoodDiaryLogic fdlInstance = (FoodDiaryLogic) session.getAttribute("foodDiaryJSP");
            this.todayString = fdlInstance.getTodayString();
            this.dateAdded = parseDatesInstance.parseDatesLongFormat(this.todayString);
        }
    }

    public List<Float> returnListOfCaloriesPerMeal() {
        FooddiaryQueries fooddiaryQueriesInstance = new FooddiaryQueries();
        List<Float> caloriesPerMeal = new ArrayList<Float>();
        for (Iterator<String> i = constantInstance.MEALS.iterator(); i.hasNext(); ) {
            String item = i.next();
            Float totalCaloriesPerMeal = fooddiaryQueriesInstance.retrieveSumOfCaloriesByUsernameDateAndMeal(
                    this.user, this.dateAdded, item);
            if (totalCaloriesPerMeal != null) {
                caloriesPerMeal.add(totalCaloriesPerMeal);
            } else {
                caloriesPerMeal.add(0.0f);
            }
        }
        return caloriesPerMeal;
    }

    public void createNewFoodDiaryEntry(FoodDatabaseQueries foodDatabaseQueriesInstance, Float unitsConsumed, String
                foodName, Date today, UserEntity user, String meal) {
        FooddiaryQueries fooddiaryQueriesInstance = new FooddiaryQueries();
        FooddatabaseEntity fooddatabaseItem = foodDatabaseQueriesInstance.retrieveFoodItem(foodName);
        Float totalCalories = unitsConsumed * fooddatabaseItem.getCalories();
        Float quantity = unitsConsumed * fooddatabaseItem.getServingSize();
        Float caloriesPerItem = fooddatabaseItem.getCalories();
        /*FooddiaryEntity fooddiaryEntityInstance = new FooddiaryEntity();*/
        fooddiaryQueriesInstance.createFoodDiaryEntry(today, meal, caloriesPerItem, quantity,
                totalCalories, fooddatabaseItem, user);

    }
}
