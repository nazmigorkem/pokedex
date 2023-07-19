package FileIO;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("./test.txt")) {
            fileWriter.write("test text.");
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
