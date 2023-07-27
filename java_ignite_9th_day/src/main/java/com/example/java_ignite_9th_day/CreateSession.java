package com.example.java_ignite_9th_day;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name="CreateSession", value = "/create-session")
public class CreateSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Cookie cookie = new Cookie("test", session.getId());
        cookie.setMaxAge(5 * 60);
        resp.addCookie(cookie);
        session.setAttribute("name", "Ignite");
    }
}
