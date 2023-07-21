package tech.obss.jip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDAO {
    Database database;

    public ContactDaoImpl(Database database) {
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
    public void deleteStudent(int id) {
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
    public void addStudent(Contact contact) {
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
}
