package tech.obss.jip;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Contact implements Insertable {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;

    public Contact(int id, String firstName, String lastName, int phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public void insert() {
        try (PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(
                "INSERT INTO " +
                        "Contact (id, firstName, lastName, phoneNumber, email)" +
                        "VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setInt(4, phoneNumber);
            statement.setString(5, email);
            statement.execute();
            Database.getInstance().getConnection().commit();
        } catch (SQLException e) {
            try {
                Database.getInstance().getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
