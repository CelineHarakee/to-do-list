import java.util.Scanner;

import javax.swing.JOptionPane;

public class DeadlineTask extends Task {

    protected String deadlineDateTime;

    // Constructor
    public DeadlineTask(String title, String dueDate, int priority, String deadlineDateTime) {
        super(title, dueDate, priority); // Call superclass constructor
        this.deadlineDateTime = deadlineDateTime;
    }

    // Getter and Setter
    public String getDeadlineDateTime() {
        return deadlineDateTime;
    }

    public void setDeadlineDateTime(String deadlineDateTime) {
        this.deadlineDateTime = deadlineDateTime;
    }

    // Override 
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call superclass method
        System.out.println("Deadline Date and Time: " + deadlineDateTime);
    }

    public static DeadlineTask createDeadlineTask() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
    
        System.out.println("Enter due date:");
        String dueDate = scanner.nextLine();
    
        System.out.println("Enter priority (an integer):");
        int priority = scanner.nextInt();
    
        scanner.nextLine();
    
        System.out.println("Enter deadline date and time:");
        String deadlineDateTime = scanner.nextLine();
    
        return new DeadlineTask(title, dueDate, priority, deadlineDateTime);
    }
    
}
