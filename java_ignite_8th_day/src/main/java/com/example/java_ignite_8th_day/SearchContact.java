package com.example.java_ignite_8th_day;

import com.example.java_ignite_8th_day.Database.Database;
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

@WebServlet(name = "SearchContact", value = "/searchContact")
public class SearchContact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Contact> contactList = new ArrayList<>();
        ContactDAOImpl contactDAO = new ContactDAOImpl(Database.getInstance());
        if (id == null) {
            req.setAttribute("contacts", contactDAO.getAllContacts());
            req.getRequestDispatcher("resultPage.jsp").forward(req, resp);
            return;
        }
        Contact contact = contactDAO.getContactByID(Integer.parseInt(id));
        if (contact == null) {
            resp.sendRedirect("searchContact.jsp");
            return;
        }
        contactList.add(contact);
        req.setAttribute("contacts", contactList);
        req.getRequestDispatcher("resultPage.jsp").forward(req, resp);
    }
}
