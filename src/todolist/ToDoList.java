package todolist;

//Container Class
//Responsible for containing other objects
//Controls adding, removing, and getting objects from the container class

//PA3 course class is container class

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ToDoList implements Iterable<Task> {

    private ArrayList<Task> toDoList;

    //Constructor to instantiate the array list
    public ToDoList(){
        toDoList = new ArrayList<>();
    }

    //Two methods for adding to the list, overloaded method
    //Method One, pass a task in
    public void addTask(Task task){

        //Adds the address of the task object to Array List
        toDoList.add(task);
    }

    //Method Two, build the task and add it to the list
    public void addTask(String name, int priority, boolean completed){

        //Alternative line to write, useful for debugging
        //Task task = new Task(name, priority, completed);

        //Creates a task object
        //Passes the parameters of the task constructor to build a new task and add it to the list
        this.addTask(new Task(name, priority, completed));
    }

    //Method: Get Task, returns type Task
    //One based index, but Array Lists are still 0 based index
    public Task getTask(int index){
        index--;
        if(index < 0 || index > toDoList.size()){
            throw new IndexOutOfBoundsException();
        }

        //Task returnTask = new Task(toDoList.get(index));
        //return returnTask;

        //Calls get method on the toDoList, and returns the Task, uses the copy constructor
        //It's its own object and has a different memory location
        //Deep Copy
        return new Task(toDoList.get(index));


    }

    public Task removeTask(int index) {
        index--;
        if (index < 0 || index > toDoList.size()) {
            throw new IndexOutOfBoundsException();
        }

        Task returnTask = new Task(toDoList.get(index));
        toDoList.remove(index);

        return returnTask;
    }

    public Task updateTask(int index, Task task){
        index--;
        if (index < 0 || index > toDoList.size()) {
            throw new IndexOutOfBoundsException();
        }

        //Method Chaining
        toDoList.get(index).setPriority(task.getPriority());
        toDoList.get(index).setCompleted(task.getCompleted());

        return new Task(toDoList.get(index));
    }

    public int count(){
        return toDoList.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return toDoList.iterator();
    }

    public void sort(){

        //Collections is a class of functions that work on a collection
        Collections.sort(toDoList, Collections.reverseOrder());
    }

    @Override
    public String toString(){
        String result = "";

        for(int i = 0; i < toDoList.size(); i++){
            result += (i + 1) + ". " + toDoList.get(i) + "\n";
        }

        return result;
    }

}//Public Class Delimiter
