package day3.labSerializationoutputstream;

import java.io.FileOutputStream;
import java.io.*;

public class Student implements Serializable {

    private String name;
    private int id;
    private transient Course course;

    public Student(String name, int id, Course course) {
        this.name = name;
        this.id = id;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", course=" + course.toString() +
                '}';
    }
    public String toString2() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +

                '}';
    }

    public static void main(String[] args) {
        Course course1 = new Course("Math",1);
        Student student = new Student("Alex",2, course1);


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the Student object
        Student deserializedStudent = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            deserializedStudent = (Student) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print the deserialized Student object
        System.out.println("Deserialized Student: " + deserializedStudent.toString2());
    }
    }



