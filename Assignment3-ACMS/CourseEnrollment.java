import java.util.ArrayList;
import java.util.List;

public class CourseEnrollment {
    private int enrollmentID;
    private int studentID;
    private List<Assessment> assessments;

    public CourseEnrollment(int enrollmentID, int studentID) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.assessments = new ArrayList<>();
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }


}

