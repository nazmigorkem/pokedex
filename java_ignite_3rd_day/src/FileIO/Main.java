package FileIO;

public class Main {
    public static void main(String[] args) {
        InputStreamFileOperations inputStreamFileOperations = new InputStreamFileOperations();
        inputStreamFileOperations.writeFile("./test.txt", "test text.");
        String data = inputStreamFileOperations.read("./test.txt");
        System.out.println(data);
    }
}
