package tech.obss.jip;


public class App {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Contact contact = new Contact(1, "test", "a", 1, "b");
        ContactDAO contactDAO = new ContactDaoImpl(database);
        contactDAO.addStudent(contact);
        contactDAO.getAllContacts().forEach(System.out::println);
    }
}

