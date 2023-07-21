package tech.obss.jip;

import java.util.List;

public interface ContactDAO {
    public List<Contact> getAllContacts();

    public void deleteStudent(int id);

    public void addStudent(
            Contact contact
    );
}
