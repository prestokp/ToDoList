package todolist;

//The class Task implements multiple interfaces
public class Task implements Priority, Comparable<Task>{

    private String name;
    private int priority;
    private boolean completed;

    //Overloaded constructors, same name, different parameter lists
    public Task(String name, int priority, boolean completed){
        this.name = name;
        this.priority = priority;
        this.completed = completed;

    }

    public Task(String name, int priority){
        //'This' is a call to the constructor above
        this(name, priority, false);
    }

    public Task(String name){
        this(name, 0, false);
    }

    //Copy constructor, constructor that copies the object of the same type, makes a new copy of the old object
    //Creating a Task object from another task object
    public Task(Task task){
        this(task.name, task.priority, task.completed);
    }

    @Override
    public int compareTo(Task o){
        //If the return value is 0 then both of the tasks are the same priority, negative number
        //equals lower priority, positive number equals higher priority
        return priority - o.getPriority();
    }

    @Override
    public void setPriority(int priority){
        this.priority = priority;
    }

    @Override
    public int getPriority(){
        return priority;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public boolean getCompleted(){
        return completed;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name + " " + priority + " " + completed;
    }

}//Public Class Delimiter
