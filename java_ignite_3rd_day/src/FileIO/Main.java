package FileIO;

public class Main {
    public static void main(String[] args) {
        Benchmark<InputStreamFileOperations> inputStreamFileOperationsBenchmark = new Benchmark<>(new InputStreamFileOperations());
        inputStreamFileOperationsBenchmark.startFullTest();

        Benchmark<FileReaderFileOperations> fileReaderFileOperationsBenchmark = new Benchmark<>(new FileReaderFileOperations());
        fileReaderFileOperationsBenchmark.startFullTest();

        Benchmark<BufferedStreamFileOperations> bufferedStreamFileOperationsBenchmark = new Benchmark<>(new BufferedStreamFileOperations());
        bufferedStreamFileOperationsBenchmark.startFullTest();
    }
}
