package Occurences;

public class Main {
    public static void main(String[] args) {
        OccurrenceFinder occurrenceFinder = new OccurrenceFinder();
        occurrenceFinder.parseWords();
        occurrenceFinder.countWords();
        occurrenceFinder.printWords();
        
    }
}
