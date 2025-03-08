package application;

import javafx.beans.property.*;

public class Transaction {
    private final StringProperty type;
    private final StringProperty category;
    private final DoubleProperty amount;
    private final StringProperty date;

    public Transaction(String type, String category, double amount, String date) {
        this.type = new SimpleStringProperty(type);
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleStringProperty(date);
    }

    public StringProperty typeProperty() { return type; }
    public StringProperty categoryProperty() { return category; }
    public DoubleProperty amountProperty() { return amount; }
    public StringProperty dateProperty() { return date; }
}
