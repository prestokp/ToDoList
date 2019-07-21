package todolist;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ToDoListDriver {

    //Main is a static method, static objects can only access other static objects
    static ToDoList list = new ToDoList();
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        buildList("todolist.txt");

        char choice = '1';
        do{
            //Output our list and a menu
            System.out.println("\nCurrent List: ");
            System.out.println(list);
            System.out.println();
            System.out.println("Options");
            System.out.println("\t1. Add a new task");
            System.out.println("\t2. Remove a task");
            System.out.println("\t3. Modify a task");
            System.out.println("\t4. Sort task by priority");
            System.out.println("\t5. Save list");
            System.out.println("\t6. Quit");
            System.out.println("Enter an option (1-6): ");
            choice = keyboard.nextLine().charAt(0);

            switch (choice){
                case '1': //add
                    addTask();
                    break;

                case '2': //remove
                    removeTask();
                    break;

                case '3': //modify
                    modifyTask();
                    break;

                case '4': //sort
                    list.sort();
                    break;

                case '5': //save
                    saveList("todolist.txt");
                    break;

                case '6': //quit
                    break;

                default:
                    System.out.println(choice + " is not a valid option");
                    break;
            }

        }while(choice != '6');

    }

    public static void buildList(String filename){
        Scanner sc = null;

        //Throws an excpetion if the file fails to open
        try{
            sc = new Scanner(new File(filename));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        while (sc.hasNext()){
            String strTask = sc.nextLine();
            Scanner line = new Scanner(strTask);
            line.useDelimiter(",");
            String name, priority, completed;

            //Example of method chaining, line is a scanner, next takes a string, trim is a method on the string
            name = line.next().trim();
            priority = line.next().trim();
            completed = line.next().trim();

            //Wrapper class of the primitive types so that we can treat them as objects
            list.addTask(new Task(name, Integer.parseInt(priority), Boolean.parseBoolean(completed)));
        }
    }

    public static void addTask(){

        String name, priority, completed;
        System.out.println();
        System.out.println("Add task: ");
        System.out.print("Name of task: ");
        name = keyboard.nextLine();
        System.out.print("Priority of task (0-10): ");
        priority = keyboard.nextLine();
        System.out.print("Is this task completed (y/n): ");
        completed = keyboard.nextLine();
        completed = completed.trim(); //This line is for defensive programming purposes

        //toUppercase is a static method that is part of the Character class
        if (Character.toUpperCase(completed.charAt(0)) == 'Y'){
            completed = "true";
        }
        else{
            completed = "false";
        }

        list.addTask(name, Integer.parseInt(priority), Boolean.parseBoolean(completed));
    }

    public static void removeTask(){
        int index;

        System.out.println();
        System.out.print("Enter the number of the task to remove: ");

        //nextInt leaves the new line in the stream,
        index = keyboard.nextInt();
        //the line below takes the new line out of the stream
        keyboard.nextLine();

        //This portion of code does not throw an exception as shown in the other class, because this driver class
        //communicates with a user
        //The ToDoList java class communicates with the program, the Driver class communicates with the user
        if(index < 1 || index > list.count()){
            System.out.println("Not a valid task number");
            return;
        }

        list.removeTask(index);
    }

    public static void modifyTask(){
        int index;

        System.out.println();
        System.out.print("Enter the number of the task to modify: ");
        index = keyboard.nextInt();
        keyboard.nextLine();

        if(index < 1 || index > list.count()){
            System.out.println("Not a valid task number");
            return;
        }

        Task task = list.getTask(index);

        System.out.println("Priority for " + task.getName() + ": ");
        task.setPriority(keyboard.nextInt());
        keyboard.nextLine();
        System.out.print("Is the task " + task.getName() + " complete? (Y/N): ");
        task.setCompleted(Character.toUpperCase(keyboard.nextLine().charAt(0)) == 'Y');

        list.updateTask(index, task);
    }

    public static void saveList(String fileName){
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(fileName, "UTF-8");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        for (Task task : list){
            writer.println(task.getName() + ","
                    + task.getPriority() + "," +
                    task.getCompleted());
        }

        writer.close();
    }


}
