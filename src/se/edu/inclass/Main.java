package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        printData(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlinesStream(tasksData);
        printDatastream(tasksData);


        for(Task t: filterByString(tasksData,"11")){
            System.out.println(t);
        }


    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDatastream(ArrayList<Task> tasksData){
        System.out.println("print using stream");
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printDeadlinesStream(ArrayList<Task> tasksData){
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }
    public static ArrayList<Task> filterByString(ArrayList<Task> taskData, String filterString){
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) taskData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredTaskList;

    }
    public static void printDeadlineStream(ArrayList<Task> tasksData){
        tasksData.stream()
                .filter((s) -> s instanceof Deadline)
                .sorted((a,b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out :: println);
    }
    public static int countDeadlinesStream(ArrayList<Task> tasksData) {
        System.out.println("count stream");
        int count;
        count = (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
        return count;
    }





}
