 import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

    private static List<Task> tasks = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Task operations");
            System.out.println("2. Category Operations");
            System.out.println("3. Exit");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (mainChoice) {
                case 1:
                    taskOperations();
                    break;

                case 2:
                    manageCategoriesAndTasks();
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void taskOperations() {
        while (true) {
            System.out.println("Choose a task operation:");
            System.out.println("1. Create Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createTask();
                    break;

                case 2:
                    System.out.println("Enter the task title to edit:");
                    String taskNametoEdit = scanner.nextLine();
                    Task.editTask(tasks, taskNametoEdit);
                    break;

                case 3:
                    System.out.println("Enter the task title to delete:");
                    String taskName = scanner.nextLine();
                    Task.deleteTask(tasks, taskName);
                    break;

                case 4:
                    return; 

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

         private static void manageCategoriesAndTasks() {
        while (true) {
            System.out.println("Choose a category operation:");
            System.out.println("1. Create Category");
            System.out.println("2. Add Task to Category");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createCategory();
                    break;
                case 2:
                    addTaskToCategory();
                    break;
                 case 3:
                    Category.editCategory(categories);
                     break;
                case 4:
                    System.out.println("Enter the name of the category to delete:");
                    String categoryNameToDelete = scanner.nextLine();
                    Category.deleteCategory(categories, categoryNameToDelete);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createCategory() {
        System.out.print("Enter the name of the category: ");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        categories.add(category);
        System.out.println("Category created: " + categoryName);
    }

    private static void addTaskToCategory() {
        if (categories.isEmpty()) {
            System.out.println("No categories available. Create a category first.");
            return;
        }
    
        // Display existing tasks
        System.out.println("Choose a task to add to the category:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getTitle());
        }
    
        int taskChoice = scanner.nextInt();
    
        if (taskChoice < 1 || taskChoice > tasks.size()) {
            System.out.println("Invalid task choice. Please try again.");
            return;
        }
    
        Task selectedTask = tasks.get(taskChoice - 1);
    
        // Display existing categories
        System.out.println("Choose a category to add the task:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
    
        int categoryChoice = scanner.nextInt();
    
        if (categoryChoice < 1 || categoryChoice > categories.size()) {
            System.out.println("Invalid category choice. Please try again.");
            return;
        }
    
        Category selectedCategory = categories.get(categoryChoice - 1);
        selectedCategory.addTask(selectedTask);
    
        System.out.println("Task added to category: " + selectedCategory.getName());
    }
    


    private static void createTask() {
        System.out.println("Choose a task type:");
        System.out.println("1. Simple Task");
        System.out.println("2. Recurring Task");
        System.out.println("3. Deadline Task");

        int taskTypeChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (taskTypeChoice) {
            case 1:
                tasks.add(Task.createTask());
                System.out.println("Simple Task created and added to the list.");
                break;

            case 2:
                tasks.add(RecurringTask.createRecurringTask());
                System.out.println("Recurring Task created and added to the list.");
                break;

            case 3:
                tasks.add(DeadlineTask.createDeadlineTask());
                System.out.println("Deadline Task created and added to the list.");
                break;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    
    }
}