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
 * Servlet to handle SignUp
 */
@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {

    HashPassword hashPassword = new HashPassword();
    Urls urlClass = new Urls();
    UserQueriesBean userProcesses = new UserQueriesBean();
    // TODO: submit unique titles for each jsp in header.jsp
    // TODO: admin account-delete usernames, or individual can delete their own account

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPath = req.getServletPath();
        String url = urlClass.createFullUrlString(userPath);

        try {
            resp.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
            IOException {
        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");
        String url;
        List existingUsers = userProcesses.validateUser(username);
        if (!existingUsers.isEmpty()) {
            url = urlClass.createFullUrlString("/signup");
            session.setAttribute("invalidUsername",
                    "Form was rejected because username already exists.");
            try {
                response.sendRedirect(url);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        String password = request.getParameter("password");
        String hashedPassword = hashPassword.hashPassword(password);
        url = urlClass.createFullUrlString("/signup_page2");
        session.setAttribute("username", username);
        session.setAttribute("hashedPassword", hashedPassword);

        try {
            response.sendRedirect(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}


