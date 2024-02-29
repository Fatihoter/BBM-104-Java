public class MultipleChoiceAssessment implements Assessment {
    public MultipleChoiceAssessment() {
        super();
    }

    @Override
    public void addTask(Task task) {
        // MultipleChoiceAssessment'a özgü görev ekleme işlemleri
    }

    @Override
    public void printTasks() {
        // MultipleChoiceAssessment'a özgü görev yazdırma işlemleri
    }

    @Override
    public int fee() {
        // MultipleChoiceAssessment'a özgü ücret hesaplama işlemleri
        return 15;
    }

    @Override
    public Task[] getTasks() {
        return new Task[0];
    }
}
