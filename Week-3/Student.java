class Student {
    String name;
    byte age;
    char grade;
    float marks;

    // Constructor to initialize the student's name, age, and marks
    public Student(String name, byte age, float marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Method to calculate the grade based on marks
    public char gradeCalculator() {
        if (marks >= 90 && marks <= 100) {
            grade = 'A';
        } else if (marks >= 80 && marks < 90) {
            grade = 'B';
        } else if (marks >= 70 && marks < 80) {
            grade = 'C';
        } else if (marks >= 60 && marks < 70) {
            grade = 'D';
        } else if (marks < 60) {
            grade = 'F';
        }
        return grade; // Return the calculated grade
    }

    // Method to display student information
    public void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + gradeCalculator()); // Call gradeCalculator() to get the grade
    }

    public static void main(String[] args) {
        // Create a Student object with example data
        Student student = new Student("Ali", (byte) 20, 85.5f);
        
        // Display student info
        student.displayInfo();
    }
}
