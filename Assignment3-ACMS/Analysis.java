// Analysis.java
public class Analysis implements Task {

    public int fee = 10;
    public Analysis() {
    }

    @Override
    public void print() {
        System.out.println("Analysis");
    }



    @Override
    public String getDescription() {
        return null;
    }
}
