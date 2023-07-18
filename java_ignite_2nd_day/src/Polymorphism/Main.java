package Polymorphism;

public class Main {
    public static void main(String[] args) {
        Employee e = new Employee();
        Person p = e;
        Main.someMethod(e);
    }

    private static void someMethod(Person p) {
            Employee employee = (Employee) p;
    }
}
