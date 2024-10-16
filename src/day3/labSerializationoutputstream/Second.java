package day3.labSerializationoutputstream;

import java.io.*;

public class Second {

        public static void main(String[] args) {
            Course course = new Course("Math", 101);
            Student student = new Student("Alice", 123, course);

            // Serialize the Student object
            try  {
                FileOutputStream fos = new FileOutputStream("student.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(student);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Deserialize the Student object
            Student deserializedStudent = null;
            try  {
                FileInputStream fis = new FileInputStream("student.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                deserializedStudent = (Student) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Print the deserialized Student object
            System.out.println("Deserialized Student: " + deserializedStudent.toString());
        }


}
