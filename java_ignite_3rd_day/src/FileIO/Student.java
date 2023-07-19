package FileIO;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String department;
    private int schoolNumber;
    private String address;

    public Student(int id, String name, String department, int schoolNumber, String address) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.schoolNumber = schoolNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
