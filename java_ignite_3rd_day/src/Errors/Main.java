package Errors;

public class Main {
    public static void main(String[] args) {
        try {
            myFunction(2);
        } catch (MyException myException) {
            System.out.println("Error handled.");
        }
    }

    public static void myFunction(int number) throws MyException {
        if (number % 2 != 0) {
            throw new MyException();
        } else {
            System.out.println("The number is even.");
        }
    }
}
