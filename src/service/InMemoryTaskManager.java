package service;
import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private int taskId = 0;
    public HashMap<Integer,Task> tasks;
    public HashMap<Integer, Epic> epics;
    public HashMap<Integer, SubTask> subTasks;
    public InMemoryTaskManager(){
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    @Override
    public void createTask(Task task){
        task.setId(taskId);
        task.setStatus(Status.NEW);
        tasks.put(task.getId(),task);
        taskId++;
    }

    @Override
    public void createEpic(Epic epic){
        epic.setId(taskId);
        epic.setStatus(Status.NEW);
        epics.put(taskId,epic);
        taskId++;
    }

    @Override
    public void createSubTask(SubTask subTask){
        subTask.setId(taskId);
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(),subTask);
        Epic epic = epics.get(subTask.getEpicId());
        epic.setSubTasksId(subTask.getId());
        taskId++;
    }

    @Override
    public void updateTask(Task task){
        if(!tasks.containsKey(task.getId())) return;
        tasks.put(task.getId(),task);
    }

    @Override
    public void updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) return;
        epics.put(epic.getId(),epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if(!subTasks.containsKey(subTask.getId()) && !epics.containsKey(subTask.getEpicId())) return;
        subTasks.put(subTask.getId(), subTask);
    }

    @Override
    public List<Task> getTasks() { return new ArrayList<>(tasks.values()); }

    @Override
    public List<Epic> getEpics() { return new ArrayList<>(epics.values()); }

    @Override
    public List<SubTask> getSubTasks() { return new ArrayList<>(subTasks.values()); }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTaskById(int id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public List<SubTask> getEpicsSubTask(Epic epic) {
        ArrayList<SubTask> epicsSubTask = new ArrayList<>();
        for (SubTask sub : subTasks.values()) {
            if (sub.getEpicId() == epic.getId()) epicsSubTask.add(sub);
        }
        return epicsSubTask;
    }

    @Override
    public void deleteTasks() { tasks.clear(); }

    @Override
    public void deleteEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void deleteSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) epic.deleteSubTasksId();
    }

    @Override
    public void deleteTaskById(int id) { tasks.remove(id); }

    @Override
    public void deleteEpicById(int id) {
        for (int index : epics.get(id).getSubTasksId()) subTasks.remove(index);
        epics.remove(id);
    }

    @Override
    public void deleteSubTaskById(int id) {
        Epic epic = epics.get(subTasks.get(id).getEpicId());
        epic.subTasksId.remove(epic.subTasksId.indexOf(subTasks.get(id).getId()));
        for (int index : epic.getSubTasksId()) {
            SubTask sub = subTasks.get(index);
            if (sub.getStatus().equals(Status.NEW)) { epic.setStatus(Status.NEW); }
            if (sub.getStatus().equals(Status.DONE)) { epic.setStatus(Status.DONE); }
            else epic.setStatus(Status.IN_PROGRESS);
        }
        updateEpic(epic);
        subTasks.remove(id);
    }

    @Override
    public void updateStatus(Task task, Status status) {
        task.setStatus(status);
        tasks.put(task.getId(),task);
    }

    @Override
    public void updateStatus(SubTask subTask, Status status) {
        Epic epic = epics.get(subTask.getEpicId());
        subTask.setStatus(status);
        for (int id : epic.getSubTasksId()) {
            SubTask sub = subTasks.get(id);
            if (sub.getStatus().equals(Status.NEW)) { epic.setStatus(Status.NEW); }
            if (sub.getStatus().equals(Status.DONE)) { epic.setStatus(Status.DONE); }
            else epic.setStatus(Status.IN_PROGRESS);
        }
        subTasks.put(subTask.getId(),subTask);
        epics.put(epic.getId(),epic);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }


}

