package day3.labSerializationoutputstream;

import java.io.Serializable;

public class Course {
    private String courseName;
    private int courseID;

    public Course(String courseName, int courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseID=" + courseID +
                '}';
    }
}
