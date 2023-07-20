package Collections;

public class Student implements Comparable<Student>{
    int number;

    public Student(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public int hashCode() {
        return this.number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        return this.number == ((Student) obj).number;
    }
}
