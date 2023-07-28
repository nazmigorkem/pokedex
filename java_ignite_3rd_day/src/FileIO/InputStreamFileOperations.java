package FileIO;

import java.io.*;

public class InputStreamFileOperations implements FileOperations {
    @Override
    public String read(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "ISO-8859-9")) {
                int currentChar;
                while ((currentChar = inputStreamReader.read()) != -1) {
                    stringBuilder.append((char) currentChar);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void writeFile(String filePath, String data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "ISO-8859-9");
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public String getOperationName() {
        return "Input Stream File Operations";
    }
}
