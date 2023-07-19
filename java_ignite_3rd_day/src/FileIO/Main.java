package FileIO;

public class Main {
    public static void main(String[] args) {
        InputStreamFileOperations inputStreamFileOperations = new InputStreamFileOperations();
        inputStreamFileOperations.writeFile("./test.txt", "test text.");
        String inputStreamData = inputStreamFileOperations.read("./test.txt");
        System.out.println(inputStreamData);

        BufferedStreamFileOperations bufferedStreamFileOperations = new BufferedStreamFileOperations();
        bufferedStreamFileOperations.writeFile("./test.txt", "test text.");
        String bufferedInputStreamData = bufferedStreamFileOperations.read("./test.txt");
        System.out.println(bufferedInputStreamData);
    }
}
