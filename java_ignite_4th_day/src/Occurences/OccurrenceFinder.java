package Occurences;

import FileIO.BufferedStreamFileOperations;

import java.util.*;

public class OccurrenceFinder {
    private final String text;
    private final TreeSet<Word> map = new TreeSet<>();
    private List<String> parsedWords = new ArrayList<>();

    public OccurrenceFinder() {
        BufferedStreamFileOperations bufferedStreamFileOperations = new BufferedStreamFileOperations();
        this.text = bufferedStreamFileOperations.read("./test.txt");
    }

    public void parseWords() {
        this.parsedWords = Arrays.asList(text.split("[\\p{Punct} ]+"));
    }

    public void countWords() {
        for (String currentWord: parsedWords) {
            Word word = new Word(currentWord);
            if (map.contains(word)) {
                continue;
            }
            word.setCount(Collections.frequency(parsedWords, word.getWord()));
            map.add(word);
        }
    }

    public void printWords() {
        for (Word key: this.getSet()) {
            System.out.println(key.getWord() + " " + key.getCount());
        }
    }

    public TreeSet<Word> getSet() {
        return map;
    }
}
