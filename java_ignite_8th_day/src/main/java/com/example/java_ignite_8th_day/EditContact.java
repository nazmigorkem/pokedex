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

@WebServlet(name="EditContact", value="/editContact")
public class EditContact extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactDAOImpl contactDAO = new ContactDAOImpl(Database.getInstance());
        Contact contact = contactDAO.parseRequest(req);
        contactDAO.editContact(contact);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        req.setAttribute("contacts", contactList);
        req.getRequestDispatcher("resultPage.jsp").forward(req, resp);
    }
}
