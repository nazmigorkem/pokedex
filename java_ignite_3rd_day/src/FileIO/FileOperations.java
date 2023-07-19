package FileIO;

public interface FileOperations {
    String read(String filePath);
    String writeFile(String filePath, String data);
}
