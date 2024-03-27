package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubTask(SubTask subTask);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<SubTask> getSubTasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    void deleteTasks();

    void deleteEpics();

    void deleteSubTasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    SubTask getSubTaskById(int id);

    List<SubTask> getEpicsSubTask(Epic epic);

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubTaskById(int id);

    void updateStatus(Task task, Status status);

    void updateStatus(SubTask subTask, Status status);
    List<Task> getHistory();
}
