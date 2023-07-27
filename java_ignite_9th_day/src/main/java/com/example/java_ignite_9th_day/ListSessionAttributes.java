package com.example.java_ignite_9th_day;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name="ListSessionAttributes", value = "/list-session-attributes")
public class ListSessionAttributes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        req.getSession().getAttributeNames().asIterator().forEachRemaining(name -> {
            out.println(name + " : " + req.getSession().getAttribute(name) + " : " + new Date(req.getSession().getCreationTime()));
        });
    }
}
