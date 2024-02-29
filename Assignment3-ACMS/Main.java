import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFileName = args[0];
        String outputFileName = "output.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

        StudentDAO studentDAO = new StudentDAO();
        CourseEnrollmentDAO courseEnrollmentDAO = new CourseEnrollmentDAO();

        studentDAO.readStudentData("student1.txt");
        courseEnrollmentDAO.readEnrollmentData("courseEnrollment1.txt");

        // Reading input file
        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                processCommand(command, studentDAO, courseEnrollmentDAO, writer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    private static void processCommand(String command, StudentDAO studentDAO, CourseEnrollmentDAO courseEnrollmentDAO, BufferedWriter writer) throws IOException {
        String[] parts = command.split(" ");
        String commandType = parts[0];

        switch (commandType) {
            case "AddStudent":
                addStudent(parts, studentDAO, writer);
                break;
            case "RemoveStudent":
                removeStudent(parts, studentDAO, writer);
                break;
            case "CreateEnrollment":
                createEnrollment(parts, courseEnrollmentDAO, writer);
                break;
            case "AddAssessment":
                addAssessment(parts, courseEnrollmentDAO, writer);
                break;
            case "TotalFee":
                calculateTotalFee(parts, courseEnrollmentDAO, writer);
                break;
            case "ListStudents":
                listStudents(studentDAO, writer);
                break;
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }

    private static void addStudent(String[] parts, StudentDAO studentDAO, BufferedWriter writer) throws IOException {
        int studentID = Integer.parseInt(parts[1]);
        String name = parts[2];
        String surname = parts[3];
        String phoneNumber = parts[4];
        StringBuilder addressBuilder = new StringBuilder();
        for (int i = 5; i < parts.length; i++) {
            addressBuilder.append(parts[i]);
            if (i < parts.length - 1) {
                addressBuilder.append(" ");
            }
        }
        String address = addressBuilder.toString();

        Student newStudent = new Student(studentID, name, surname, phoneNumber, address);
        studentDAO.add(newStudent);
        writer.write("Student " + studentID + " " + name + " added\n");

        // student1.txt dosyasına ekleme yapın
        try (BufferedWriter studentFileWriter = new BufferedWriter(new FileWriter("student1.txt", true))) {
            // Yeni öğrenciyi dosyaya yazın

            studentFileWriter.write("\n"+studentID + "\t" + name + " " + surname + " \t" + phoneNumber + "\tAddress: " + address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeStudent(String[] parts, StudentDAO studentDAO, BufferedWriter writer) throws IOException {
        int removedStudentID = Integer.parseInt(parts[1]);

        Student removedStudent = studentDAO.deleteByID(removedStudentID);
        if (removedStudent != null) {
            writer.write("Student " + removedStudentID + " " + removedStudent.getName() + " removed\n");
        } else {
            writer.write("Student with ID " + removedStudentID + " not found.\n");
        }
    }

    private static void createEnrollment(String[] parts, CourseEnrollmentDAO courseEnrollmentDAO, BufferedWriter writer) throws IOException {
        int enrollmentID = Integer.parseInt(parts[1]);
        int studentIDForEnrollment = Integer.parseInt(parts[2]);
        CourseEnrollment enrollment = new CourseEnrollment(enrollmentID, studentIDForEnrollment);
        courseEnrollmentDAO.createEnrollment(enrollment);
        writer.write("CourseEnrollment " + enrollmentID + " created\n");
    }

    private static void addAssessment(String[] parts, CourseEnrollmentDAO courseEnrollmentDAO, BufferedWriter writer) throws IOException {
        int enrollmentID = Integer.parseInt(parts[1]);
        String assessmentType = parts[2];
        String[] tasks = Arrays.copyOfRange(parts, 3, parts.length);

        Assessment newAssessment = courseEnrollmentDAO.createAssessment(assessmentType, tasks);
        courseEnrollmentDAO.addAssessment(enrollmentID, newAssessment);

        if (assessmentType.equals("MultipleChoice")) {
            writer.write("MultipleChoice assessment added to enrollment " + enrollmentID + "\n");
        } else if (assessmentType.equals("Essaybased")) {
            writer.write("Essaybased assessment added to enrollment " + enrollmentID + "\n");
        }
    }

    private static void calculateTotalFee(String[] parts, CourseEnrollmentDAO courseEnrollmentDAO, BufferedWriter writer) throws IOException {
        int enrollmentIDForTotalFee = Integer.parseInt(parts[1]);
        int totalFee = courseEnrollmentDAO.calculateTotalFee(enrollmentIDForTotalFee);
        writer.write("Total fee for enrollment " + enrollmentIDForTotalFee + ": " + totalFee + "\n");
    }

    private static void listStudents(StudentDAO studentDAO, BufferedWriter writer) throws IOException {
        ArrayList<Student> students = studentDAO.getAll();

        // Öğrencileri isme göre sıralayın
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                // İsimleri karşılaştırarak sıralama yapın
                return student1.getName().compareTo(student2.getName());
            }
        });

        writer.write("Student List:\n");
        // Sıralanmış öğrencileri dosyaya yazdırın
        for (Student student : students) {
            writer.write(student.toString() + "\n");
        }
    }
}
