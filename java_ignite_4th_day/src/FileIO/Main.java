package FileIO;

public class Main {
    public static void main(String[] args) {
        ObjectStreamFileOperations objectStreamFileOperations = new ObjectStreamFileOperations();
        Student student = new Student("testName", new Course(1), 2);
        objectStreamFileOperations.serialize(student, "./test.txt");
    }
}
