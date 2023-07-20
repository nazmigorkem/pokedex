package FileIO;

public interface FileOperations {
    String read(String filePath);
    void writeFile(String filePath, String data);
}
