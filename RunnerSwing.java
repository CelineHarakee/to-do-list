import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RunnerSwing {
    private static List<Task> tasks = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static JFrame frame;
    private static Stack<JPanel> panelStack = new Stack<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RunnerSwing::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Welcome Page
        JPanel welcomePanel = createWelcomePanel();
        panelStack.push(welcomePanel);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("Button.background", new Color(0, 0, 255));  
        defaults.put("Button.foreground", new Color(255, 255, 255));  
        welcomePanel.setBackground(new Color(220, 224, 240)); 
        
        frame.add(welcomePanel);
        frame.setVisible(true);
    }

    private static JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JLabel welcomeLabel = new JLabel("Welcome to Todo List App!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(73, 90, 166));
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 23));
        welcomeLabel.setBackground(new Color(220, 224, 240)); 

        JButton taskOperationsButton = new JButton("Task Operations");
        taskOperationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskOperationsButton.setForeground(new Color(73, 90, 166)); 

        JButton categoryOperationsButton = new JButton("Category Operations");
        categoryOperationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryOperationsButton.setForeground(new Color(73, 90, 166)); 

        JButton showTasksButton = new JButton("Show Tasks");
        showTasksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showTasksButton.setForeground(new Color(73, 90, 166)); 


        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(20)); // padding
        panel.add(taskOperationsButton);
        panel.add(categoryOperationsButton);
        panel.add(showTasksButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // place it in the center
        frame.setVisible(true);
    

        panel.add(Box.createVerticalGlue());
        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // padding
        panel.add(showTasksButton);
        panel.add(taskOperationsButton);
        panel.add(categoryOperationsButton);
        panel.add(Box.createVerticalGlue());

        taskOperationsButton.addActionListener(e -> showTaskOperations());
        categoryOperationsButton.addActionListener(e -> showCategoryOperations());
        showTasksButton.addActionListener(e -> showTasks());

        return panel;
    }

    private static void showTasks() {
        frame.getContentPane().removeAll();
        JPanel tasksPanel = new JPanel(new GridLayout(categories.size(), 1));
        tasksPanel.setBackground(new Color(220, 224, 240)); 

        for (Category category : categories) {
            JLabel categoryLabel = new JLabel("Category: " + category.getName());
            categoryLabel.setBackground(new Color(220, 224, 240)); // background color for the category label
            categoryLabel.setOpaque(true);
            tasksPanel.add(categoryLabel);
    
            for (Task task : category.getTasks()) {
                JLabel taskLabel = new JLabel(task.displayTaskDetails());
                taskLabel.setBackground(new Color(220, 224, 240)); //  background color for the task label
                taskLabel.setOpaque(true); 

                JButton markCompletedButton = new JButton("Mark as Completed");
                markCompletedButton.addActionListener(e -> markTaskAsCompleted(task));
                markCompletedButton.setBackground(new Color(220, 224, 240)); // background color for the button
                markCompletedButton.setForeground(new Color(73, 90, 166)); 
                markCompletedButton.setOpaque(true);

                JPanel taskPanel = new JPanel();
                tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
                taskPanel.add(taskLabel, BorderLayout.CENTER);
                taskPanel.add(markCompletedButton, BorderLayout.WEST);

                taskPanel.setBackground(new Color(220, 224, 240)); // background color for the task panel
                taskPanel.setOpaque(true);

                taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // padding
                tasksPanel.add(taskPanel);
            }
        }
    
        JPanel backButtonPanel = createBackButtonPanel();
        panelStack.push(backButtonPanel);
    
        frame.add(tasksPanel, BorderLayout.CENTER);
        frame.add(backButtonPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
    

    private static JPanel createBackButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(73, 90, 166)); 

    
        backButton.addActionListener(e -> goBack());
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    
        alignComponentsCenter(backButton);
    
        buttonPanel.add(backButton);
    
        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    private static void showTaskOperations() {
        frame.getContentPane().removeAll();
        JPanel taskPanel = createTaskPanel();
        taskPanel.setBackground(new Color(220, 224, 240)); 
        panelStack.push(taskPanel);
        frame.add(taskPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void showCategoryOperations() {
        frame.getContentPane().removeAll();
        JPanel categoryPanel = createCategoryPanel();
        panelStack.push(categoryPanel);
        frame.add(categoryPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static JPanel createTaskPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JButton createTaskButton = new JButton("Create Task");
        createTaskButton.setForeground(new Color(73, 90, 166)); 

        JButton editTaskButton = new JButton("Edit Task");
        editTaskButton.setForeground(new Color(73, 90, 166));

        JButton deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.setForeground(new Color(73, 90, 166));

        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(73, 90, 166));
    
        addActionListenersForTaskButtons(createTaskButton, editTaskButton, deleteTaskButton, backButton);
    
        alignComponentsCenter(createTaskButton, editTaskButton, deleteTaskButton, backButton);
    
        panel.add(createTaskButton, gbc);
        panel.add(editTaskButton, gbc);
        panel.add(deleteTaskButton, gbc);
        panel.add(backButton, gbc);
    
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // frame in the center of the screen
        frame.setVisible(true);
    
        return panel;
    }
    

    private static void addActionListenersForTaskButtons(JButton createTaskButton, JButton editTaskButton,
                                                        JButton deleteTaskButton, JButton backButton) {
        createTaskButton.addActionListener(e -> createTask());
        editTaskButton.addActionListener(e -> editTask());
        deleteTaskButton.addActionListener(e -> {
            String taskNameToDelete = JOptionPane.showInputDialog(frame, "Enter the task title to delete:");
            deleteTask(tasks, taskNameToDelete);
        });
        
        backButton.addActionListener(e -> goBack());
    }

    private static void addActionListenersForCategoryButtons(
        JButton createCategoryButton, JButton addTaskToCategoryButton,
        JButton deleteCategoryButton, JButton editCategoryButton, JButton backButton) {

    createCategoryButton.addActionListener(e -> createCategory());
    addTaskToCategoryButton.addActionListener(e -> addTaskToCategory());
    deleteCategoryButton.addActionListener(e -> deleteCategory());
    editCategoryButton.addActionListener(e -> editCategory());
    backButton.addActionListener(e -> goBack());
}


    private static JPanel createCategoryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JButton createCategoryButton = new JButton("Create Category");
        createCategoryButton.setForeground(new Color(73, 90, 166));

        JButton addTaskToCategoryButton = new JButton("Add task to category");
        addTaskToCategoryButton.setForeground(new Color(73, 90, 166));

        JButton deleteCategoryButton = new JButton("Delete Category");
        deleteCategoryButton.setForeground(new Color(73, 90, 166));

        JButton editCategoryButton = new JButton("Edit Category");
        editCategoryButton.setForeground(new Color(73, 90, 166));

        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(73, 90, 166));

    
        addActionListenersForCategoryButtons(createCategoryButton, addTaskToCategoryButton,
                deleteCategoryButton, editCategoryButton, backButton);
    
        alignComponentsCenter(createCategoryButton, addTaskToCategoryButton,
                deleteCategoryButton, editCategoryButton, backButton);
    
        panel.add(createCategoryButton, gbc);
        panel.add(addTaskToCategoryButton, gbc);
        panel.add(deleteCategoryButton, gbc);
        panel.add(editCategoryButton, gbc);
        panel.add(backButton, gbc);
    
        panel.setBackground(new Color(220, 224, 240)); 
    
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // frame in the center of the screen
        frame.setVisible(true);
    
        return panel;
    }
    
    
    private static void createTask() {
        int taskTypeChoice = showOptionDialog("Choose a task type:", "Task Type",
                new String[]{"Simple Task", "Recurring Task", "Deadline Task"});

        switch (taskTypeChoice) {
            case 0:
                tasks.add(Task.createTask());
                JOptionPane.showMessageDialog(frame, "Simple Task created and added to the list.");
                break;
            case 1:
                tasks.add(RecurringTask.createRecurringTask());
                JOptionPane.showMessageDialog(frame, "Recurring Task created and added to the list.");
                break;
            case 2:
                tasks.add(DeadlineTask.createDeadlineTask());
                JOptionPane.showMessageDialog(frame, "Deadline Task created and added to the list.");
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid choice. Please enter a valid option.");
        }
    }

    private static void editTask() {
        String taskNameToEdit = JOptionPane.showInputDialog(frame, "Enter the task title to edit:");
        
        if (taskNameToEdit != null && !taskNameToEdit.trim().isEmpty()) {
            String newTaskName = JOptionPane.showInputDialog(frame, "Enter the new task title:");
            
            if (newTaskName != null && !newTaskName.trim().isEmpty()) {
                for (Task task : tasks) {
                    if (task.getTitle().equals(taskNameToEdit)) {
                        task.setTitle(newTaskName);
                        JOptionPane.showMessageDialog(frame, "Task edited: " + taskNameToEdit + " -> " + newTaskName);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Task not found: " + taskNameToEdit);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid new task name. Please enter a valid name.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid task name. Please enter a valid name.");
        }
    }
    
    public static void deleteTask(List<Task> tasks, String taskNameToDelete) {
        Task taskToRemove = null;
    
        // Find the task to delete
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTitle().equalsIgnoreCase(taskNameToDelete)) {
                taskToRemove = task;
                tasks.remove(i); // Remove the task from the list
                break;
            }
        }
    
        if (taskToRemove != null) {
            // Manually remove the task from categories
            for (Category category : categories) {
                category.removeTask(taskToRemove);
            }
    
            System.out.println("Task deleted: " + taskNameToDelete);
            showTasks();  // Update the UI
        } else {
            System.out.println("Task not found: " + taskNameToDelete);
        }
    }
    

    static void createCategory() {
        categories.add(Category.createCategory());
        JOptionPane.showMessageDialog(frame, "Category created and added to the list.");
    }

    static void editCategory() {
        String categoryNameToEdit = JOptionPane.showInputDialog(frame, "Enter the name of the category to edit:");
    
        if (categoryNameToEdit != null && !categoryNameToEdit.trim().isEmpty()) {
            String newCategoryName = JOptionPane.showInputDialog(frame, "Enter the new name for the category:");
    
            if (newCategoryName != null && !newCategoryName.trim().isEmpty()) {
                for (Category category : categories) {
                    if (category.getName().equals(categoryNameToEdit)) {
                        category.setName(newCategoryName);
                        JOptionPane.showMessageDialog(frame, "Category edited: " + categoryNameToEdit + " -> " + newCategoryName);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Category not found: " + categoryNameToEdit);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid new category name. Please enter a valid name.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid category name. Please enter a valid name.");
        }
    }
    
    static void addTaskToCategory() {
        if (categories.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No categories available. Create a category first.");
            return;
        }
    
        // Create an array of task titles for the user to choose from
        String[] taskTitles = tasks.stream().map(Task::getTitle).toArray(String[]::new);
    
        // user choose a task
        String selectedTaskTitle = (String) JOptionPane.showInputDialog(
                frame,
                "Choose a task to add to the category:",
                "Select Task",
                JOptionPane.QUESTION_MESSAGE,
                null,
                taskTitles,
                taskTitles[0]);
    
        if (selectedTaskTitle != null) {
            // find the task by title
            Task selectedTask = tasks.stream()
                    .filter(task -> task.getTitle().equals(selectedTaskTitle))
                    .findFirst()
                    .orElse(null);
    
            if (selectedTask != null) {
                // Create an array of category names for the user to choose from
                String[] categoryNames = categories.stream().map(Category::getName).toArray(String[]::new);
    
                //  user choose a category
                String selectedCategoryName = (String) JOptionPane.showInputDialog(
                        frame,
                        "Choose a category to add the task:",
                        "Select Category",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        categoryNames,
                        categoryNames[0]);
    
                if (selectedCategoryName != null) {
                    // find the category by name
                    Category selectedCategory = categories.stream()
                            .filter(category -> category.getName().equals(selectedCategoryName))
                            .findFirst()
                            .orElse(null);
    
                    if (selectedCategory != null) {
                        // Add the selected task to the selected category
                        selectedCategory.addTask(selectedTask);
    
                        JOptionPane.showMessageDialog(frame, "Task added to category: " + selectedCategoryName);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Selected category not found.");
                    }
                } else {
                    // User canceled the operation
                    JOptionPane.showMessageDialog(frame, "Operation canceled by the user.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Selected task not found.");
            }
        } else {
            // User canceled the operation
            JOptionPane.showMessageDialog(frame, "Operation canceled by the user.");
        }
    }
    
    


    static void deleteCategory() {
        String categoryNameToDelete = JOptionPane.showInputDialog(frame, "Enter the name of the category to delete:");
        for (Category category : categories) {
            if (category.getName().equals(categoryNameToDelete)) {
                categories.remove(category);
                JOptionPane.showMessageDialog(frame, "Category deleted: " + category.getName());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Category not found: " + categoryNameToDelete);
    }

    private static int showOptionDialog(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(frame, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private static void alignComponentsCenter(JComponent... components) {
        for (JComponent component : components) {
            component.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    static void goBack() {
        if (!panelStack.isEmpty()) {
            panelStack.pop(); // Remove 
            if (!panelStack.isEmpty()) {
                JPanel previousPanel = panelStack.peek();
                frame.getContentPane().removeAll();
                frame.add(previousPanel);
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    private static void markTaskAsCompleted(Task task) {
    task.markAsCompleted();
    showTasks();
}

}