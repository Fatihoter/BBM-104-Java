public class EssayBasedAssessment implements Assessment {
    public EssayBasedAssessment() {
        super();
    }

    @Override
    public void addTask(Task task) {
        // EssayBasedAssessment'a özgü görev ekleme işlemleri
    }

    @Override
    public void printTasks() {
        // EssayBasedAssessment'a özgü görev yazdırma işlemleri
    }

    @Override
    public int fee() {
        // EssayBasedAssessment'a özgü ücret hesaplama işlemleri
        return 10;
    }
    @Override
    public Task[] getTasks() {
        // EssayBasedAssessment için özel işlemleri burada gerçekleştirin (örneğin, boş bir liste dönebilirsiniz).
        return null; // Örneğin, boş bir liste dönebilirsiniz.
    }
}
