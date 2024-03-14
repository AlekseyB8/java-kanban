package model;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task{
    public ArrayList<Integer> subTasksId = new ArrayList<>();

    public Epic(String taskName, String description) {
        super(taskName, description);
    }

    public void setSubTasksId(int id) { subTasksId.add(id); }

    public ArrayList<Integer> getSubTasksId() { return subTasksId; }

    public void updateSubTaskId(Epic epic) { this.subTasksId = epic.subTasksId; }

    public int getSubTaskById(int id) { return  subTasksId.get(id); }

    public void deleteSubTasksId() { subTasksId.clear(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasksId, epic.subTasksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasksId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasksId=" + subTasksId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                "}\n";
    }
}
