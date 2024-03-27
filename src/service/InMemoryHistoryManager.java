package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements  HistoryManager {

    private static final int MAX_TASKS_IN_HISTORY = 10;

    private final ArrayList<Task> tasksHistory = new ArrayList<>();

    @Override
    public List<Task> getHistory() {
        return  new ArrayList<>(tasksHistory);
    }

    @Override
    public void add(Task task) {
        if (task == null) return;
        if (tasksHistory.size() >= MAX_TASKS_IN_HISTORY) {
            tasksHistory.removeFirst();
        }
        tasksHistory.add(task);
    }
}
