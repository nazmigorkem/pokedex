package FileIO;

import Shapes.Drawable;

public class Benchmark<T extends FileOperations> {
    private long startTime;
    private long stopTime;
    private final String reallyLongText;
    private final String FILE_PATH = "./test.txt";
    private final T streamer;

    public Benchmark(T streamer) {
        this.streamer = streamer;
        reallyLongText = "Lorem ipsum".repeat(1_000_000);
    }

    public void startWriteTest() {
        this.startTime = System.currentTimeMillis();
        streamer.writeFile(FILE_PATH, reallyLongText);
        this.stopTime = System.currentTimeMillis();
    }

    public void startReadTest() {
        this.startTime = System.currentTimeMillis();
        streamer.read(FILE_PATH);
        this.stopTime = System.currentTimeMillis();
    }

    public void startFullTest() {
        System.out.println("Starting writing test.");
        this.startWriteTest();
        this.printBenchmarkResults();

        System.out.println("Starting reading test.");
        this.startReadTest();
        this.printBenchmarkResults();
    }

    public void printBenchmarkResults() {
        System.out.printf("Took %d ms\n", stopTime - startTime);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
