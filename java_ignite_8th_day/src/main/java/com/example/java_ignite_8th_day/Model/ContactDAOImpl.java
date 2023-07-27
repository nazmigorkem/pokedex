package com.example.java_ignite_8th_day.Model;
import com.example.java_ignite_8th_day.Database.Database;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {
    private final Database database;

    public ContactDAOImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Contact> getAllContacts() {
        String SQL = "SELECT * FROM CONTACT";
        List<Contact> contactList = new ArrayList<>();
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int phoneNumber = resultSet.getInt("phoneNumber");
                String email = resultSet.getString("email");
                contactList.add(new Contact(id, firstName, lastName, phoneNumber, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    @Override
    public Contact getContactByID(int id) {
        String SQL = "SELECT * FROM CONTACT WHERE id=?";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int phoneNumber = resultSet.getInt("phoneNumber");
            String email = resultSet.getString("email");
            return new Contact(id, firstName, lastName, phoneNumber, email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteContact(int id) {
        String SQL = "DELETE FROM Contact Where id=?";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            database.getConnection().commit();
        } catch (SQLException e) {
            try {
                database.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }


    @Override
    public void editContact(Contact contact) {
        String SQL = "UPDATE Contact SET firstName=?, lastName=?, phoneNumber=?, email=? WHERE id=?";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL)) {
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setInt(3, contact.getPhoneNumber());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.setInt(5, contact.getId());
            preparedStatement.executeUpdate();
            database.getConnection().commit();
        } catch (SQLException e) {
            try {
                database.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addContact(Contact contact) {
        String SQL = "INSERT INTO Contact VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL)) {
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.setString(2, contact.getFirstName());
            preparedStatement.setString(3, contact.getLastName());
            preparedStatement.setInt(4, contact.getPhoneNumber());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.execute();
            database.getConnection().commit();
        } catch (SQLException e) {
            try {
                database.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contact parseRequest(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        String email = req.getParameter("email");
        return new Contact(id, firstName, lastName, phoneNumber, email);
    }
}
