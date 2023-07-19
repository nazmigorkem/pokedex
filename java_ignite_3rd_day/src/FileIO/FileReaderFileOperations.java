package FileIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileOperations implements FileOperations {
    @Override
    public String read(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            int currentChar;
            while ((currentChar = fileReader.read()) != -1) {
                System.out.print((char) currentChar);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    @Override
    public String writeFile(String filePath, String data) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;
    }
}
