import java.util.Objects;

public class Task {
    protected String taskName;
    protected String description;
    private int taskId;
    private String status;
    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW.toString();
    }
    public void setTaskId(int taskId){
        this.taskId = taskId;
    }

    public void setStatus(String status) {
        if (status.equals(Status.NEW.toString())
                || status.equals(Status.IN_PROGRESS.toString())
                || status.equals(Status.DONE.toString())) {
            this.status = status;
        }
    }
    public String getStatus() {
        return status;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", taskId=" + taskId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId && Objects.equals(taskName, task.taskName) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, description, taskId, status);
    }
}
