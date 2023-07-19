package FileIO;

public class Main {
    public static void main(String[] args) {
        FileReaderFileOperations fileReaderFileOperations = new FileReaderFileOperations();
        InputStreamFileOperations inputStreamFileOperations = new InputStreamFileOperations();
        BufferedStreamFileOperations bufferedStreamFileOperations = new BufferedStreamFileOperations();

        System.out.println("-----Input Stream----");
        Benchmark<InputStreamFileOperations> inputStreamFileOperationsBenchmark = new Benchmark<>(inputStreamFileOperations);
        inputStreamFileOperationsBenchmark.startFullTest();

        System.out.println("-----File Reader----");
        Benchmark<FileReaderFileOperations> fileReaderFileOperationsBenchmark = new Benchmark<>(fileReaderFileOperations);
        fileReaderFileOperationsBenchmark.startFullTest();

        System.out.println("----Buffered Stream----");
        Benchmark<BufferedStreamFileOperations> bufferedStreamFileOperationsBenchmark = new Benchmark<>(bufferedStreamFileOperations);
        bufferedStreamFileOperationsBenchmark.startFullTest();
    }
}
