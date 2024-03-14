package model;

import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected Status status;
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() { return id; }

    public void setStatus(Status status) { this.status = status; }

    public Status getStatus(){ return  status; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }
}
