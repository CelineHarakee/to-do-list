import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;

public class Category {

    // Fields
    private String name;
    private List<Task> tasks;

    // Constructor
    public Category(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // Method to add a task to the category
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to remove a task from the category
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Method to display category details
    public void displayDetails() {
        System.out.println("Category Name: " + name);
        System.out.println("Tasks in the Category:");
        for (Task task : tasks) {
            System.out.println("- " + task.getTitle());
        }
    }

    // Static method to create a new category
    public static Category createCategory() {
        Scanner scanner = new Scanner(System.in);

        // Get category details from the user
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();

        // Create and return the category
        return new Category(categoryName);
    }

    // Static method to edit the category
    public static void editCategory(List<Category> categories) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the category to edit:");
        String categoryNameToEdit = scanner.nextLine();

        for (Category category : categories) {
            if (category.getName().equals(categoryNameToEdit)) {
                System.out.println("Current details of the category:");
                category.displayDetails();

                System.out.println("Enter the new name for the category:");
                String newName = scanner.nextLine();
                category.setName(newName);

                System.out.println("Category details edited successfully. New name: " + newName);
                return;
            }
        }

        System.out.println("Category not found.");
    }

    // Static method to delete the category
    public static void deleteCategory(List<Category> categories, String categoryNameToDelete) {
        for (Category category : new ArrayList<>(categories)) {
            if (category.getName().equals(categoryNameToDelete)) {
                categories.remove(category);
                System.out.println("Category has been deleted: " + categoryNameToDelete);
                return;
            }
        }

        System.out.println("Category not found.");
    }

    // Static method to add a task to a category
    public static void addTaskToCategory(List<Category> categories) {
        if (categories.isEmpty()) {
            System.out.println("No categories available. Create a category first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

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

        Task task = Task.createTask(); 
        selectedCategory.addTask(task);

        System.out.println("Task added to category: " + selectedCategory.getName());
    }
    
    
}
