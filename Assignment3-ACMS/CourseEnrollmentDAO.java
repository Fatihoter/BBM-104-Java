import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CourseEnrollmentDAO {
    private List<CourseEnrollment> courseEnrollments;
    private Map<Integer, CourseEnrollment> enrollments;

    public CourseEnrollmentDAO() {
        this.courseEnrollments = new ArrayList<>();
        this.enrollments = new HashMap<>();
    }

    public void readEnrollmentData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            CourseEnrollment currentEnrollment = null;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // Boş satırları atla
                }

                String[] parts = line.split("\t");

                // Kurs kaydının başlangıcını kontrol et
                if (Character.isDigit(parts[0].charAt(0))) {
                    // Yeni bir CourseEnrollment nesnesi oluştur
                    int enrollmentID = Integer.parseInt(parts[0]);
                    int studentID = Integer.parseInt(parts[1]);
                    currentEnrollment = new CourseEnrollment(enrollmentID, studentID);
                    courseEnrollments.add(currentEnrollment);
                    enrollments.put(enrollmentID, currentEnrollment);
                } else if (currentEnrollment != null) {
                    // Değerlendirme ve görevleri ekleyin
                    String assessmentType = parts[0];
                    String[] tasks = parts.length > 1 ? parts[1].split(" ") : new String[0];

                    Assessment assessment = createAssessment(assessmentType, tasks);
                    currentEnrollment.addAssessment(assessment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public List<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void addCourseEnrollment(CourseEnrollment enrollment) {
        courseEnrollments.add(enrollment);
        enrollments.put(enrollment.getEnrollmentID(), enrollment);
    }

    public CourseEnrollment getEnrollmentByID(int enrollmentID) {
        return enrollments.get(enrollmentID);
    }

    public void createEnrollment(CourseEnrollment enrollment) {
        courseEnrollments.add(enrollment);
        enrollments.put(enrollment.getEnrollmentID(), enrollment);
        courseEnrollments.sort(Comparator.comparingInt(CourseEnrollment::getEnrollmentID));
    }

    public void addAssessment(int enrollmentID, Assessment assessment) {
        CourseEnrollment enrollment = getEnrollmentByID(enrollmentID);
        if (enrollment != null) {
            enrollment.addAssessment(assessment);
        }
    }

    public int calculateTotalFee(int enrollmentIDForTotalFee) {
        int totalFee = 0;

        for (CourseEnrollment enrollment : courseEnrollments) {
                List<Assessment> assessments = enrollment.getAssessments();
                if (assessments != null) {
                    for (Assessment assessment : assessments) {
                        if (assessment.equals("Essaybased")) {
                            totalFee += 10;
                        } else if (assessment.equals("MultipleChoice")) {
                            totalFee += 15;
                        }
                        Task[] tasks = assessment.getTasks();
                        if (tasks != null) {
                            for (Task task : tasks) {
                                if (task.equals("Analysis")) {
                                    totalFee += 10;
                                } else if (task.equals("AdditionalTasks")) {
                                    totalFee += 15;
                                } else if (task.equals("LiteratureReview")) {
                                    totalFee += 15;
                                } else if (task.equals("QuestionSet")) {
                                    totalFee += 7;
                                }
                            }
                        }
                    }
                }
        }

        return totalFee;
    }

    public Assessment createAssessment(String assessmentType, String[] tasks) {
        Assessment assessment;
        // Değerlendirme tipini büyük/küçük harfe duyarlı olmayacak şekilde kontrol et
        String formattedAssessmentType = assessmentType.toLowerCase();

        switch (formattedAssessmentType) {
            case "essaybased":
                assessment = new EssayBasedAssessment();
                break;
            case "multiplechoice":
                assessment = new MultipleChoiceAssessment();
                break;
            default:
                throw new IllegalArgumentException("Invalid assessment type: " + assessmentType);
        }

        for (String taskType : tasks) {
            Task task = createTask(taskType);
            assessment.addTask(task);
        }
        return assessment;
    }


    public Task createTask(String taskType) {
        switch (taskType) {
            case "Analysis":
                return new Analysis();
            case "LiteratureReview":
                return new LiteratureReview();
            case "QuestionSet":
                return new QuestionSet();
            case "AdditionalTasks":
                return new AdditionalTasks();
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }
}
