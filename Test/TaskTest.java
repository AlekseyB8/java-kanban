import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.*;
import service.HistoryManager;
import service.Managers;
import service.TaskManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();
    Task task = new Task("Test addNewTask", "Test addNewTask description");
    Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description");
    SubTask subTask = new SubTask("Test addNewSubtask", "Test addNewSubtask description");

    @Test
    void addNewTask() {
        taskManager.createTask(task);
        final Task savedTask = taskManager.getTaskById(task.getId());

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        taskManager.createEpic(epic);
        final Epic savedEpic = taskManager.getEpicById(epic.getId());

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }

    @Test
    void addNewSubTask() {
        taskManager.createEpic(epic);
        subTask.setEpicId(epic.getId());
        taskManager.createSubTask(subTask);
        final SubTask savedSubTask = taskManager.getSubTaskById(subTask.getId());

        assertNotNull(savedSubTask, "Задача не найдена.");
        assertEquals(subTask, savedSubTask, "Задачи не совпадают.");

        final List<SubTask> subTasks = taskManager.getSubTasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество задач.");
        assertEquals(subTask, subTasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addHistory() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}
