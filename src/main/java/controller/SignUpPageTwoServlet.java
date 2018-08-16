package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import entity.UserEntity;
import entity.UserprofileEntity;
import logic.*;
import session.UserProfileQueriesBean;
import session.UserQueriesBean;
import sun.plugin.util.UserProfile;

import static java.lang.Math.abs;
import static java.lang.Math.round;


@WebServlet(name = "SignUpPageTwoServlet")
public class SignUpPageTwoServlet extends HttpServlet {

    ParseDates date = new ParseDates();
    CalculateNetCalories calculateNetCalories = new CalculateNetCalories();
    UserQueriesBean newUser = new UserQueriesBean();
    UserProfileQueriesBean newUserProfile = new UserProfileQueriesBean();
    Urls urlClass = new Urls();
    DateTimeImpl dateTime = new DateTimeImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");

        String hashedPassword = request.getParameter("hashedPassword");
        Integer heightFt = Integer.parseInt(request.getParameter("heightFt"));
        Integer heightIn = Integer.parseInt(request.getParameter("heightIn"));
        Float weight = Float.valueOf(request.getParameter("weight"));
        String birthDateString = request.getParameter("birthdate");
        Date birthDate = date.parseDate(birthDateString);
        String gender = request.getParameter("gender");
        String lifestyle = request.getParameter("lifestyle");
        String weightGoal = request.getParameter("weightGoals");

        CalculateBMR userBMR = new CalculateBMR(heightFt, heightIn, weight, birthDate,
                gender, lifestyle);
        Float BMR = userBMR.getBMR();

        Float suggestedCaloriesPerDay = calculateNetCalories.calculateNetCalories(
                BMR, weightGoal, gender);
        suggestedCaloriesPerDay = round(suggestedCaloriesPerDay * 100F) / 100F;
        Float changeInPoundsPerWeek = ((suggestedCaloriesPerDay - BMR) * 7) / 3500;
        changeInPoundsPerWeek = round(changeInPoundsPerWeek * 100F) / 100F;
        Float projectedWeightInFiveWeeks = changeInPoundsPerWeek * 5;
        projectedWeightInFiveWeeks = round(projectedWeightInFiveWeeks * 100F) / 100F;
        String fiveWeeksFromNow = date.returnDateFiveWeeksFromNow();

        String gainOrLoss;
        String gainOrLose;
        if (projectedWeightInFiveWeeks > 0) {
            gainOrLoss = "gain";
            gainOrLose = "gain";
        } else {
            gainOrLoss = "loss";
            gainOrLose = "lose";
        }
        // Don't want display goals to say you will lose negative pounds
        changeInPoundsPerWeek = abs(changeInPoundsPerWeek);
        projectedWeightInFiveWeeks = abs(projectedWeightInFiveWeeks);

        UserEntity user = new UserEntity();
        newUser.createUser(user, username, hashedPassword);
        UserprofileEntity userprofile = new UserprofileEntity();
        newUserProfile.createUserProfile(userprofile, user, heightFt, heightIn, weight, gender, weightGoal,
                BMR, suggestedCaloriesPerDay, lifestyle, birthDate, changeInPoundsPerWeek, gainOrLoss,
                gainOrLose, projectedWeightInFiveWeeks);

        // TODO: gainOrLoss and gainOrLose shouldn't be in database. set these as variables using jstl
        session.setAttribute("userprofile", userprofile);
        session.setAttribute("fiveWeeksFromNow", fiveWeeksFromNow);

        String url = urlClass.createFullUrlString("/displayGoals");

        try {
            response.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
