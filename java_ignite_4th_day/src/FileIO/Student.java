package FileIO;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    private Course course;
    private int studentID;

    public Student(String name, Course course, int studentID) {
        this.name = name;
        this.course = course;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
