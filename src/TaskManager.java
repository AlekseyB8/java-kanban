import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int taskId = 0;
    public HashMap<Integer,Task> tasks;
    public HashMap<Integer,Epic> epics;
    public TaskManager(){
        tasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public void createTask(Task task){
        task.setTaskId(taskId);
        tasks.put(taskId,task);
        taskId++;
    }
    public void createEpic(Epic epic){
        epic.setTaskId(taskId);
        epics.put(taskId,epic);
        taskId++;
    }
    public void createSubTask(Epic epic,SubTask sub){
        sub.setTaskId(taskId);
        epics.get(epic.getTaskId()).addSubTask(sub);
        taskId++;
    }
    public void printAll(){
        if(tasks.isEmpty()) {
            System.out.println("Список задач пуст!");
        } else {
            for (Task task : tasks.values()){
                System.out.println(task);
            }
            System.out.println();
        }
        if(epics.isEmpty()){
            System.out.println("Список эпиков пуст!");
        } else {
            for (Epic epic : epics.values()){
                System.out.println(epic);
                System.out.println(epic.getSubTasks());
            }
            System.out.println();
        }

    }
    public void clearAll() {
        if (!tasks.isEmpty()) {
            tasks.clear();
            System.out.println("Список задач очишен!");
        } else System.out.println("Список задач пуст!");
        if (!epics.isEmpty()) {
            epics.clear();
            System.out.println("Список эпиков очищен!");
        } else System.out.println("Список эпиков пуст!");
    }
    public Task getById(int id){
        if(tasks.containsKey(id)) return tasks.get(id);
        if(epics.containsKey(id)) return epics.get(id);
        else {
            for(Epic epic : epics.values()) {
                for (SubTask sub : epic.getSubTasks()){
                    if (sub.getTaskId() == id) return sub;
                }
            }
        }
        return null;
    }
    public void updateTask(Task task){
        tasks.get(task.getTaskId()).taskName = task.taskName;
        tasks.get(task.getTaskId()).description = task.description;
        if(tasks.get(task.getTaskId()).getStatus().equals(Status.NEW.toString())){
            tasks.get(task.getTaskId()).setStatus(Status.IN_PROGRESS.toString());
        } else if(tasks.get(task.getTaskId()).getStatus().equals(Status.IN_PROGRESS.toString())) {
            tasks.get(task.getTaskId()).setStatus(Status.DONE.toString());
        } else System.out.println("Задача уже завершена");
    }
    public void updateEpic(SubTask subTask){
        for(Epic epic : epics.values()){
            for(SubTask sub : epic.getSubTasks()){
                if(sub.getTaskId() == subTask.getTaskId()){
                    sub.taskName = subTask.taskName;
                    sub.description = subTask.description;
                    if(sub.getStatus().equals(Status.NEW.toString())){
                        sub.setStatus(Status.IN_PROGRESS.toString());
                    } else if (sub.getStatus().equals(Status.IN_PROGRESS.toString())){
                        sub.setStatus(Status.DONE.toString());
                    }
                    epics.get(epic.getTaskId()).updateStatus();
                }
            }
        }
    }
    public void deleteById(int id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
            return;
        } else if(epics.containsKey(id)){
            epics.remove(id);
            return;
        } else {
            for(Epic epic : epics.values()){
                for (SubTask sub : epic.getSubTasks()){
                    if (sub.getTaskId() == id){
                        epic.subTasks.remove(sub);
                        return;
                    }
                }
            }
        }
        System.out.println("Такой id " + id + " отсутвует");
    }
    public ArrayList<SubTask> getEpicSubTask(Epic epic){
        return epic.subTasks;
    }






}
