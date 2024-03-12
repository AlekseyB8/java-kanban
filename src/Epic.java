import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task{
    public ArrayList<SubTask> subTasks;
    public Epic(String taskName, String description) {
        super(taskName, description);
        subTasks = new ArrayList<>();
    }
    public void addSubTask(SubTask sub) {
        subTasks.add(sub);
    }
    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }
    public void updateStatus() {
        boolean checkStatus = true;
        for (SubTask sub : subTasks){
            if(!sub.getStatus().equals(Status.DONE.toString())) checkStatus = false;
            if(sub.getStatus().equals(Status.IN_PROGRESS.toString())) {
                this.setStatus(Status.IN_PROGRESS.toString());
                checkStatus = false;
                break;
            }
            if(checkStatus) this.setStatus(Status.DONE.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasks, epic.subTasks);
    }
}
