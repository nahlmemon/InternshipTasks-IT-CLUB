import java.util.Scanner;

public class ArrayBasedToDoList {
    private static String[] tasks = {
        "Buy Groceries", 
        "Read a Book", 
        "Attend Java Workshop", 
        "Complete Assignment", 
        "Prepare Presentation"
    };
    private static int taskCount = tasks.length; // Number of initial tasks

    public static void displayTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("\nTo-Do List:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayTasks(); // Show predefined tasks
        scanner.close();
    }
}
