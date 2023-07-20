package Occurences;

public class Word implements Comparable<Word>{
    private int count;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareToIgnoreCase(o.word);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Word)) return false;
        return this.compareTo((Word) obj) == 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
