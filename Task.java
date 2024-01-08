import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Task {

    // Fields
    public String title;
    protected String dueDate; 
    private int priority;
    public boolean isCompleted;
    private Category category; 


    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }


    // Constructor
    public Task(String title, String dueDate, int priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false; //false as default
    }

    // Method to mark task as completed
    public void markAsCompleted() {
        this.isCompleted = true;
    }
    


public String displayTaskDetails() {
    StringBuilder details = new StringBuilder();
    
    details.append("Title: ").append(title).append("\n");
    details.append(" -  Due Date: ").append(dueDate).append("\n");
    details.append(" -  Priority: ").append(priority).append("\n");
    details.append(" -  Completed: ").append(isCompleted).append("\n");

    // Print the details to the console (optional)
    System.out.println(details.toString());

    // Return the formatted string
    return details.toString();
}




    // Method to display task details
    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Priority: " + priority);
        System.out.println("Completed: " + isCompleted);
    }

    // Getters and Setters 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
  
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
 
 
    public static Task createTask() {
        Scanner scanner = new Scanner(System.in);

        // Get task details from the user
        System.out.println("Enter task title:");
        String title = scanner.nextLine();

        System.out.println("Enter due date:");
        String dueDate = scanner.nextLine();

        System.out.println("Enter priority (an integer):");
        int priority = scanner.nextInt();

        // Create and return the task
        return new Task(title, dueDate, priority);
    }

        // Delete task 
    public static void deleteTask(List<Task> tasks, String taskName) {
    Task taskToRemove = null;
    for (Task task : tasks) {
        if (task.getTitle().equalsIgnoreCase(taskName)) {
            taskToRemove = task;
            break;
        }
    }

    if (taskToRemove != null) {
        tasks.remove(taskToRemove);
        System.out.println("Task deleted: " + taskName);
    } else {
        System.out.println("Task not found: " + taskName);
    }
}



    // Edit task function
   public static void editTask(List<Task> tasks, String taskNametoEdit) {
    Scanner scanner = new Scanner(System.in);
    
    // Find the task with the specified name
    Task taskToEdit = null;
    for (Task task : tasks) {
        if (task.getTitle().equals(taskNametoEdit)) {
            taskToEdit = task;
            break;
        }
    }

   
    if (taskToEdit != null) {
        System.out.println("Current details of the selected task:");
        taskToEdit.displayDetails();

        // Get new details from the user
        System.out.println("Enter new title: ");
        String newTitle = scanner.next();
        taskToEdit.setTitle(newTitle);

        System.out.println("Enter new due date: ");
        String newDueDate = scanner.next();
        taskToEdit.setDueDate(newDueDate);

        System.out.println("Enter new priority: ");
        int newPriority = scanner.nextInt();
        taskToEdit.setPriority(newPriority);

        System.out.println("Task details edited successfully!");
    } else {
        // If the task is not found, print a message
        System.out.println("Task not found.");
    }
}

}




