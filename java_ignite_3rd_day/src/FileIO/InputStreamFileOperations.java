package FileIO;

import java.io.*;

public class InputStreamFileOperations implements FileOperations {
    @Override
    public String read(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "ISO-8859-9")) {
                int currentChar;
                while ((currentChar = inputStreamReader.read()) != -1) {
                    System.out.print((char) currentChar);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    @Override
    public String writeFile(String filePath, String data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "ISO-8859-9");
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
