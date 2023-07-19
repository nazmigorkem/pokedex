package FileIO;

public class Main {
    public static void main(String[] args) {
        InputStreamFileOperations inputStreamFileOperations = new InputStreamFileOperations();
        inputStreamFileOperations.writeFile("./test.txt", "test text.");
        inputStreamFileOperations.read("./test.txt");
    }
}
