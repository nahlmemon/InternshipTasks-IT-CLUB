import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + "," + phone;
    }

    public static Contact fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new Contact(parts[0], parts[1]);
        }
        return null;
    }
}

public class ContactsManager {
    private static final String FILE_NAME = "contacts.txt";
    private static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Contacts Manager =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number from 1 to 5.");
                scanner.nextLine(); // Clear input buffer
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContacts();
                    System.out.println("Contacts saved successfully. Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }

        contacts.add(new Contact(name, phone));
        System.out.println("Contact added successfully!");
    }

    private static void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\n===== Contacts List =====");
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.getName() + " | Phone: " + contact.getPhone());
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact Found!");
                System.out.println("Name: " + contact.getName() + " | Phone: " + contact.getPhone());
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter name to delete: ");
        String name = scanner.nextLine().trim();

        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Contact deleted successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private static void loadContacts() {
        File file = new File(FILE_NAME);
        try {
            if (file.createNewFile()) {
                System.out.println("New contacts file created.");
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Contact contact = Contact.fromString(line);
                    if (contact != null) {
                        contacts.add(contact);
                    }
                }
                System.out.println("Contacts loaded: " + contacts.size());
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}
