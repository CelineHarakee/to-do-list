import java.util.Scanner;

import javax.swing.JOptionPane;

public class RecurringTask extends Task {
    protected String recurrencePattern;

    // Constructor
    public RecurringTask(String title, String dueDate, int priority, String recurrencePattern) {
        super(title, dueDate, priority); // Call superclass constructor
        this.recurrencePattern = recurrencePattern;
    }

    // Getter and Setter
    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    // Override 
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call superclass method
        System.out.println("Recurrence Pattern: " + recurrencePattern);
    }
    
// Static method to create RecurringTask
    public static RecurringTask createRecurringTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter task title:");
        String title = scanner.nextLine();

        System.out.println("Enter due date:");
        String dueDate = scanner.nextLine();

        System.out.println("Enter priority (an integer):");
        int priority = scanner.nextInt();

         scanner.nextLine();
    
        System.out.println("Enter recurrence pattern:");
        String recurrencePattern = scanner.nextLine();

        return new RecurringTask(title, dueDate, priority, recurrencePattern);
    }
}
