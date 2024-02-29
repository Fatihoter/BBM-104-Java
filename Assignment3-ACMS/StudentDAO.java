import java.io.*;
import java.util.ArrayList;

public class StudentDAO {
    private static ArrayList<Student> students;

    public StudentDAO() {
        this.students = new ArrayList<>();
    }

    public void readStudentData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int studentID = Integer.parseInt(parts[0]);
                String[] nameParts = parts[1].split(" ");
                String name = nameParts[0];
                String surname = nameParts[1];
                String phoneNumber = parts[2];
                String address = parts[3].substring("Address: ".length());

                Student student = new Student(studentID, name, surname, phoneNumber, address);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void add(Student student) {
        students.add(student);
    }

    public Student deleteByID(int studentID) {
        Student removedStudent = null;
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                removedStudent = student;
                students.remove(student);
                break;
            }
        }
        return removedStudent;
    }

    public ArrayList<Student> getAll() {
        return students;
    }

    // Getter ve setter metodları
    public static Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
