import java.util.ArrayList;
import java.util.List;

// Book class to store book details
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    // Constructor to initialize book details
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;  // Initially, books are not issued
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    // Setter method to mark book as issued or returned
    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }

    // Display book details
    public void displayBookDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Issued: " + (isIssued ? "Yes" : "No"));
    }
}

// Library class to manage books
class Library {
    private List<Book> books;

    // Constructor to initialize the library
    public Library() {
        books = new ArrayList<>();
    }

    // Add a new book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Issue a book if it's available
    public void issueBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isIssued()) {
                    book.setIssued(true);
                    System.out.println("Book '" + book.getTitle() + "' has been issued.");
                    return;
                } else {
                    System.out.println("Book is already issued.");
                    return;
                }
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    // Return a book
    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isIssued()) {
                    book.setIssued(false);
                    System.out.println("Book '" + book.getTitle() + "' has been returned.");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    // Display all books in the library
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book book : books) {
            book.displayBookDetails();
            System.out.println("-----------------------------");
        }
    }
}

// Main class to test the library system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create a Library object
        Library library = new Library();

        // Add some books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
        library.addBook(new Book("1984", "George Orwell", "9780451524935"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084"));

        // Display all books in the library
        System.out.println("Displaying all books in the library:");
        library.displayAllBooks();

        // Issue a book
        library.issueBook("9780451524935");

        // Display all books after issuing a book
        System.out.println("\nDisplaying all books after issuing a book:");
        library.displayAllBooks();

        // Return the issued book
        library.returnBook("9780451524935");

        // Display all books after returning the book
        System.out.println("\nDisplaying all books after returning a book:");
        library.displayAllBooks();
    }
}
