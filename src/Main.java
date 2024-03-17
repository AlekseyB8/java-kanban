import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task1 = new Task("Task", "1");
        Task task2 = new Task("Task", "2");

        Epic epic1 = new Epic("Epic","1");
        Epic epic2 = new Epic("Epic","2");

        SubTask subTask1 = new SubTask("SubTask","1");
        SubTask subTask2 = new SubTask("SubTask","2");
        SubTask subTask3 = new SubTask("SubTask","3");

        manager.createTask(task1);
        manager.createTask(task2);
        System.out.println(manager.getTasks());

        manager.createEpic(epic1);
        manager.createEpic(epic2);
        System.out.println(manager.getEpics());
        subTask1.setEpicId(epic1.getId());
        subTask2.setEpicId(epic1.getId());
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);
        subTask3.setEpicId(epic2.getId());
        manager.createSubTask(subTask3);
        System.out.println(manager.getSubTasks());


        System.out.println("Вывод задачи с id: " + task1.getId());
        System.out.println(manager.getTaskById(task1.getId()));

        System.out.println("Вывод эпика с id: " + epic1.getId());
        System.out.println(manager.getEpicById(epic1.getId()));

        System.out.println("Вывод сабтаска с id:" + subTask1.getId());
        System.out.println(manager.getSubTaskById(subTask1.getId()));

        System.out.println("Обновление задачи");
        task1.setDescription("111");
        manager.updateTask(task1);
        task2.setDescription("222");
        manager.updateTask(task2);
        System.out.println(manager.getTasks());

        manager.updateStatus(task1, Status.IN_PROGRESS);
        manager.updateStatus(task2, Status.DONE);
        System.out.println(manager.getTasks());

        manager.deleteTaskById(task2.getId());
        System.out.println(manager.getTasks());

        System.out.println("Обновление эпика");
        epic1.setDescription("11");
        manager.updateEpic(epic1);
        subTask2.setDescription("22");
        manager.updateSubTask(subTask2);
        manager.updateStatus(subTask2, Status.IN_PROGRESS);
        System.out.println(manager.getEpics());
        System.out.println(manager.getEpicsSubTask(epic1));

        subTask3.setStatus(Status.DONE);
        manager.updateSubTask(subTask3);
        System.out.println(manager.getEpics());
        manager.deleteSubTaskById(subTask3.getId());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubTasks());

        manager.deleteEpicById(epic2.getId());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubTasks());

        manager.updateStatus(subTask1, Status.IN_PROGRESS);
        manager.updateSubTask(subTask1);
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubTasks());
        System.out.println(manager.getSubTaskById(subTask1.getId()));
        manager.deleteSubTaskById(subTask1.getId());
        manager.updateStatus(subTask2, Status.DONE);
        System.out.println(manager.getSubTasks());
        System.out.println(manager.getEpics());

        manager.deleteSubTasks();
        System.out.println(manager.getEpics());

        manager.deleteTasks();
        System.out.println(manager.getTasks());

        manager.deleteEpics();
        System.out.println(manager.getEpics());
    }
}