package com.example.java_ignite_8th_day;

import com.example.java_ignite_8th_day.Model.Contact;
import com.example.java_ignite_8th_day.Model.ContactDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditRedirection", value = "/editRedirection")
public class EditRedirection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            resp.getWriter().println("No id value supplied");
            return;
        }
        req.setAttribute("contact", new ContactDAOImpl(Database.getInstance()).getContactByID(Integer.parseInt(id)));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
