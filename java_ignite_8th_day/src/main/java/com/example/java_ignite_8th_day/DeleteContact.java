package com.example.java_ignite_8th_day;

import com.example.java_ignite_8th_day.Model.ContactDAO;
import com.example.java_ignite_8th_day.Model.ContactDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteContact", value = "/deleteContact")
public class DeleteContact extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactDAO contactDAO = new ContactDAOImpl(Database.getInstance());
        contactDAO.deleteContact(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("searchContact");
    }
}
