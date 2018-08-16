package controller;

import com.google.gson.JsonObject;
import logic.HashPassword;
import session.UserQueriesBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ValidateLoginServlet")
public class ValidateLoginServlet extends HttpServlet {

    HashPassword getHashedPassword = new HashPassword();
    UserQueriesBean getUser = new UserQueriesBean();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashedPassword = getHashedPassword.hashPassword(password);
        List userList = getUser.validateUserLogin(username, hashedPassword);
        Integer userListLength = userList.size();
        if (userListLength > 1) {
            new Exception("There should not be two of the same user");
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        JsonObject myObj = new JsonObject();

        if (userList.isEmpty()) {
            myObj.addProperty("success", false);
        } else {
            myObj.addProperty("success", true);
            myObj.addProperty("username", username);
        }
        // out.write(username);
        out.println(myObj.toString());
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
