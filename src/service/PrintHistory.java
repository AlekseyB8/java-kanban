package service;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class PrintHistory {

    public static void printTaskHistory(List<Task> tasksHistory) {
        int indexNumber = 0;
        for (Task task : tasksHistory) {
            if (task instanceof Epic epic) {
                System.out.print(++indexNumber + " " + epic);
            } else if (task instanceof SubTask subTask) {
                System.out.print(++indexNumber + " " + subTask);
            } else {
                System.out.print(++indexNumber + " " +task);
            }
        }
    }
}
