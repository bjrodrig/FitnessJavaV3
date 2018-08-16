package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import logic.HashPassword;
import logic.Urls;
import session.UserQueriesBean;

/**
 * Servlet to handle login
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends  HttpServlet {

    Urls urlClass = new Urls();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String userPath = request.getServletPath();
        String url = urlClass.createFullUrlString(userPath);

        try {
            response.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
