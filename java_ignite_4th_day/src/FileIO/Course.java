package FileIO;

import java.io.Serializable;

public class Course implements Serializable {
    private int courseID;

    public Course(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
