import java.util.ArrayList;
import java.util.List;

public class AssessmentImpl implements Assessment {
    private List<Task> tasks;

    public AssessmentImpl() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task.getDescription());
        }
    }

    public void setTasks(List<Task> task){
        tasks = task;
    }

    @Override
    public int fee() {
        // Burada ücret hesaplama mantığını implemente edebilirsiniz.
        return 0;
    }

    @Override
    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }
}
