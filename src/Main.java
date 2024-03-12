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
        manager.createEpic(epic1);
        manager.createSubTask(epic1,subTask1);
        manager.createSubTask(epic1,subTask2);
        manager.createEpic(epic2);
        manager.createSubTask(epic2,subTask3);
        manager.printAll();

        System.out.println("Вывод задачи с id: " + 1);
        System.out.println(manager.getById(1));

        System.out.println("Вывод подзадач эпика: " + 2);
        System.out.println(manager.getEpicSubTask(epic1));

        System.out.println("Обновление задачи");
        task1.description = "111";
        manager.updateTask(task1);
        task2.description = "222";
        manager.updateTask(task2);
        manager.printAll();
        manager.updateTask(task1);
        manager.printAll();

        System.out.println("Обновление эпика");
        subTask1.description = "11";
        manager.updateEpic(subTask1);
        manager.printAll();
        subTask2.description = "22";
        manager.updateEpic(subTask2);
        manager.printAll();
        subTask1.description = "1111";
        manager.updateEpic(subTask1);
        subTask2.description = "2222";
        manager.updateEpic(subTask2);
        manager.printAll();

        System.out.println("Удаление по id");
        manager.deleteById(4);
        manager.deleteById(0);
        manager.deleteById(10);
        manager.printAll();
        manager.clearAll();

    }
}

