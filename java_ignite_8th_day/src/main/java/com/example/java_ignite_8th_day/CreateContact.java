package com.example.java_ignite_8th_day;

import com.example.java_ignite_8th_day.Model.Contact;
import com.example.java_ignite_8th_day.Model.ContactDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreateContactServlet", value = "/createContact")
public class CreateContact extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        String email = req.getParameter("email");
        new ContactDAOImpl(Database.getInstance()).addContact(new Contact(id, firstName, lastName, phoneNumber, email));
    }
}
