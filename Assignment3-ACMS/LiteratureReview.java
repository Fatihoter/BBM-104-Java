// LiteratureReview.java
public class LiteratureReview implements Task {
    public LiteratureReview() {
    }

    @Override
    public void print() {
        System.out.println("LiteratureReview");
    }

    public int fee=15; // Assuming a fee of 15 for LiteratureReview task

    @Override
    public String getDescription() {
        return null;
    }
}
