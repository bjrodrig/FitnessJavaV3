package controller;

import logic.Urls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomePageServlet")
public class HomePageServlet extends HttpServlet {

    Urls urlClass = new Urls();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String url = urlClass.createFullUrlString("/homePage");
        HttpSession session = request.getSession(false);
        session.setAttribute("username", username);

        try {
            response.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = urlClass.createFullUrlString("/homePage");
        HttpSession session = request.getSession(false);

        try {
            response.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
