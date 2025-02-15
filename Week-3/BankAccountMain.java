import java.util.Date;
import java.util.Calendar;

class BankAccount {

    // Encapsulation: private fields
    private String accountHolderName, accountType;
    private char gender;
    private int accountNumber;
    private float balance;
    private Date dateOfBirth;  // Date of Birth
    private String pinCode;    // PIN Code for account security
    private String phoneNumber; // Phone number of account holder

    // Constructor
    public BankAccount(String accountHolderName, String accountType, char gender, int accountNumber, float balance, Date dateOfBirth, String pinCode, String phoneNumber) {
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.gender = gender;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateOfBirth = dateOfBirth;
        this.pinCode = pinCode;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Phone Number: " + phoneNumber);
    }

    // Inner class for Savings Account (Inheritance)
    public class SavingAccount extends BankAccount {
        private float interestRate;

        // Constructor
        public SavingAccount(String accountHolderName, String accountType, char gender, int accountNumber, float balance, Date dateOfBirth, String pinCode, String phoneNumber, float interestRate) {
            super(accountHolderName, accountType, gender, accountNumber, balance, dateOfBirth, pinCode, phoneNumber);
            this.interestRate = interestRate;
        }

        public float getInterestRate() { return interestRate; }
        public void setInterestRate(float interestRate) { this.interestRate = interestRate; }

        @Override
        public void displayAccountDetails() {
            super.displayAccountDetails();
            System.out.println("Interest Rate: " + interestRate + "%");
        }
    }

    // Inner class for Salary Account (Inheritance)
    public class SalaryAccount extends BankAccount {
        private float monthlySalary;

        // Constructor
        public SalaryAccount(String accountHolderName, String accountType, char gender, int accountNumber, float balance, Date dateOfBirth, String pinCode, String phoneNumber, float monthlySalary) {
            super(accountHolderName, accountType, gender, accountNumber, balance, dateOfBirth, pinCode, phoneNumber);
            this.monthlySalary = monthlySalary;
        }

        public float getMonthlySalary() { return monthlySalary; }
        public void setMonthlySalary(float monthlySalary) { this.monthlySalary = monthlySalary; }

        @Override
        public void displayAccountDetails() {
            super.displayAccountDetails();
            System.out.println("Monthly Salary: $" + monthlySalary);
        }
    }
}

public class BankAccountMain {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(1990, Calendar.MAY, 15);  // Year, Month, Day
        Date dob = cal.getTime();

        // Creating a BankAccount object
        BankAccount bankAccount = new BankAccount("John Doe", "Checking", 'M', 123456, 5000.0f, dob, "1234", "555-1234");

        // Creating Saving and Salary account objects
        BankAccount.SavingAccount savingAccount = bankAccount.new SavingAccount("John Doe", "Saving", 'M', 123456, 5000.0f, dob, "1234", "555-1234", 3.5f);
        BankAccount.SalaryAccount salaryAccount = bankAccount.new SalaryAccount("John Doe", "Salary", 'M', 123456, 5000.0f, dob, "1234", "555-1234", 3000.0f);

        // Displaying account details
        savingAccount.displayAccountDetails();
        salaryAccount.displayAccountDetails();
    }
}
