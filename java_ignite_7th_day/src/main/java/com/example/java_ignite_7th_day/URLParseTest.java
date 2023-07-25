package com.example.java_ignite_7th_day;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "URLParseTest", value = "/urlParseSecured")
public class URLParseTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().toLowerCase().contains("secured")) {
            resp.setStatus(403);
            resp.getWriter().println("This is secured.");
        }
    }
}
