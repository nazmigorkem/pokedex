package InnerClass;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Chapter> chapterList = new ArrayList<>();
    private Publisher publisher;

    static class Publisher {
        String name;
        String country;
    }

    class Chapter {
        private String title;
        private int chapterNumber;

        @Override
        public String toString() {
            return "Chapter{" +
                    "title='" + title + '\'' +
                    ", chapterNumber=" + chapterNumber + ", in " + chapterList.size() +
                    '}';
        }
    }
}
