package com.example.java_ignite_8th_day.Model;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ContactDAO {
    public List<Contact> getAllContacts();

    public void deleteContact(int id);

    public void editContact(Contact contact);

    public void addContact(
            Contact contact
    );

    public Contact getContactByID(int id);

    public Contact parseRequest(HttpServletRequest req);
}
