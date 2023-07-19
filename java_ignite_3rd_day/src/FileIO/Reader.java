package FileIO;

import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("./test.txt")) {
            int currentChar;
            while ((currentChar = fileReader.read()) != -1) {
                System.out.print((char) currentChar);
            }
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
