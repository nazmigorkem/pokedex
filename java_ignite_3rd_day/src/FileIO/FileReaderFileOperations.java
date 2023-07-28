package FileIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileOperations implements FileOperations {
    @Override
    public String read(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int currentChar;
            while ((currentChar = fileReader.read()) != -1) {
                stringBuilder.append(currentChar);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void writeFile(String filePath, String data) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public String getOperationName() {
        return "File Reader File Operations";
    }
}
