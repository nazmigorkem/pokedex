package com.example.java_ignite_8th_day;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ErrorHandler", value = "/errorHandler")
public class ErrorHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> attrs =  req.getAttributeNames();
        while(attrs.hasMoreElements()) {
            System.out.println(attrs.nextElement());
        }
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        if (throwable != null && throwable.getMessage().startsWith("test message")) {
            req.setAttribute("customErrorMessage", "An error has occurred as follows: " + throwable.getMessage());
            req.getRequestDispatcher("/WEB-INF/ErrorPages/500.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/ErrorPages/404.jsp").forward(req, resp);
        }
    }
}
