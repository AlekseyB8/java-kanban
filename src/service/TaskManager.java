package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int taskId = 0;
    public HashMap<Integer,Task> tasks;
    public HashMap<Integer, Epic> epics;
    public HashMap<Integer, SubTask> subTasks;
    public TaskManager(){
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createTask(Task task){
        task.setId(taskId);
        task.setStatus(Status.NEW);
        tasks.put(task.getId(),task);
        taskId++;
    }

    public void setTaskStatus(Status status, Task task) { tasks.get(task.getId()).setStatus(status); }

    public void updateTask(Task task){
        if(!tasks.containsKey(task.getId())) return;
        tasks.put(task.getId(),task);
    }

    public ArrayList<Task> getTasks() { return new ArrayList<>(tasks.values()); }

    public void deleteTasks() { tasks.clear(); }

    public Task getTaskById(int id) { return tasks.get(id); }

    public void deleteTaskById(int id) { tasks.remove(id); }

    public void createEpic(Epic epic){
        epic.setId(taskId);
        updateEpicStatus(epic);
        epics.put(taskId,epic);
        taskId++;
    }

    public void updateEpic(Epic epic){
        if (!epics.containsKey(epic.getId())) return;
        epics.put(epic.getId(),epic);
    }

    private void updateEpicStatus(Epic epic) {
        int countNew = 0;
        int countDone = 0;
        for (int id : epic.getSubTasksId()) {
            SubTask subTask = subTasks.get(id);
            if (subTask.getStatus() == Status.NEW) { countNew++; }
            if (subTask.getStatus() == Status.DONE) { countDone++; }
        }
        if (countNew == epic.getSubTasksId().size()) {
            epic.setStatus(Status.NEW);
        } else if (countDone == epic.getSubTasksId().size()) {
            epic.setStatus(Status.DONE);
        } else { epic.setStatus(Status.IN_PROGRESS); }
    }

    public ArrayList<Epic> getEpics() { return new ArrayList<>(epics.values()); }

    public Epic getEpicById(int id) { return epics.get(id); }

    public void deleteEpics() {
        epics.clear();
        subTasks.clear();
    }

    public void deleteEpicById(int id) {
        for (int index : epics.get(id).getSubTasksId()) subTasks.remove(index);
        epics.remove(id);
    }

    public void createSubTask(SubTask subTask){
        subTask.setId(taskId);
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(),subTask);
        Epic epic = epics.get(subTask.getEpicId());
        epic.setSubTasksId(subTask.getId());
        updateEpicStatus(epic);
        taskId++;
    }

    public void setSubTaskStatus(Status status, SubTask subTask) {
        subTasks.get(subTask.getId()).setStatus(status);
        Epic epic = epics.get(subTask.getEpicId());
        updateEpicStatus(epic);
    }

    public void updateSubTask(SubTask subTask) {
        if(!subTasks.containsKey(subTask.getId()) && !epics.containsKey(subTask.getEpicId())) return;
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus((epics.get(subTask.getEpicId())));
    }

    public ArrayList<SubTask> getSubTasks() { return new ArrayList<>(subTasks.values()); }

    public void deleteSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) epic.deleteSubTasksId();
    }

    public SubTask getSubTaskById(int id) { return subTasks.get(id); }

    public void deleteSubTaskById(int id) {
        Epic epic = epics.get(subTasks.get(id).getEpicId());
        epic.subTasksId.remove(epic.subTasksId.indexOf(subTasks.get(id).getId()));
        updateEpic(epic);
        subTasks.remove(id);


    }

    public ArrayList<SubTask> getEpicsSubTask(Epic epic) {
        ArrayList<SubTask> epicsSubTask = new ArrayList<>();
        for (SubTask sub : subTasks.values()) {
            if (sub.getEpicId() == epic.getId()) epicsSubTask.add(sub);
        }
        return epicsSubTask;
    }
}