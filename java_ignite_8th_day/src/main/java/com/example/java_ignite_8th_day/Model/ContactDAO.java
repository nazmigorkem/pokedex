package com.example.java_ignite_8th_day.Model;
import java.util.List;

public interface ContactDAO {
    public List<Contact> getAllContacts();

    public void deleteContact(int id);

    public void addContact(
            Contact contact
    );

    public Contact getContactByID(int id);
}
