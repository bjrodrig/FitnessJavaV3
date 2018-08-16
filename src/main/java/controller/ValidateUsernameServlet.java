package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.gson.GsonBuilder;
import logic.HashPassword;
import logic.Urls;
import session.UserQueriesBean;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Servlet to handle SignUp
 */
@WebServlet(name = "ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {

    Urls urlClass = new Urls();
    UserQueriesBean userProcesses = new UserQueriesBean();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List existingUsers = userProcesses.validateUser(username);

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

        if (existingUsers.isEmpty()) {
            myObj.addProperty("success", true);
        } else {
            myObj.addProperty("success", false);
        }

        out.println(myObj.toString());
        out.close();

    }
}