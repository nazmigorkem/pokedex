package FileIO;

public interface FileOperations {
    String getOperationName();
    String read(String filePath);
    void writeFile(String filePath, String data);
}
