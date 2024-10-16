package day4.function.labStudents;

public class Student {
    private String name;
    private String department;

    public Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    private double grade;

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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", grade=" + grade +
                '}';
    }
}
