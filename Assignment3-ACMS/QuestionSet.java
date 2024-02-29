// QuestionSet.java
public class QuestionSet implements Task {
    public QuestionSet() {
    }

    @Override
    public void print() {
        System.out.println("QuestionSet");
    }


    public int fee= 7; // Assuming a fee of 7 for QuestionSet task

    @Override
    public String getDescription() {
        // QuestionSet için özel açıklama
        return "This task involves answering a set of questions.";
    }
}
