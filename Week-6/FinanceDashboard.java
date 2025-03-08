package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FinanceDashboard extends Application {
    private TableView<Transaction> table = new TableView<>();
    private PieChart pieChart = new PieChart();
    private Label totalIncomeLabel = new Label();
    private Label totalExpenseLabel = new Label();
    private Label balanceLabel = new Label();

    // Input fields
    private TextField categoryField = new TextField();
    private TextField amountField = new TextField();
    private DatePicker datePicker = new DatePicker();

    @Override
    public void start(Stage primaryStage) {
        DatabaseHandler.createTables();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #E3F2FD;");

        // Input Section
        HBox inputBox = new HBox(10);
        categoryField.setPromptText("Category / Source");
        amountField.setPromptText("Amount");
        Button addExpenseButton = new Button("Add Expense");
        Button addIncomeButton = new Button("Add Income");

        addExpenseButton.setStyle("-fx-background-color: #FF7043; -fx-text-fill: white;");
        addIncomeButton.setStyle("-fx-background-color: #66BB6A; -fx-text-fill: white;");

        inputBox.getChildren().addAll(categoryField, amountField, datePicker, addExpenseButton, addIncomeButton);

        // Table & Chart
        tableSetup();
        pieChartSetup();
        updateDashboard();

        // Button Actions
        addExpenseButton.setOnAction(e -> addTransaction("Expense"));
        addIncomeButton.setOnAction(e -> addTransaction("Income"));

        layout.getChildren().addAll(new Label("SmartFinance Dashboard"), inputBox, table, pieChart, totalIncomeLabel, totalExpenseLabel, balanceLabel);

        Scene scene = new Scene(layout, 800, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SmartFinance");
        primaryStage.show();
    }

    private void tableSetup() {
        TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        TableColumn<Transaction, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setMinWidth(150);
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        TableColumn<Transaction, String> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        table.getColumns().addAll(typeCol, categoryCol, amountCol, dateCol);
    }

    private void pieChartSetup() {
        pieChart.setTitle("Expense vs Income");
    }

    private void addTransaction(String type) {
        String category = categoryField.getText();
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Amount must be a number!");
            return;
        }
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "N/A";

        if (category.isEmpty()) {
            showAlert("Missing Input", "Category/Source cannot be empty!");
            return;
        }

        if (type.equals("Expense")) {
            ExpenseTracker.addExpense(category, amount, date);
        } else {
            IncomeTracker.addIncome(category, amount, date);
        }

        categoryField.clear();
        amountField.clear();
        datePicker.setValue(null);

        updateDashboard();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateDashboard() {
        table.getItems().clear();
        pieChart.getData().clear();

        double totalIncome = 0, totalExpenses = 0;
        
        try (Connection conn = DatabaseHandler.connect();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM Income");
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                totalIncome += amount;
                table.getItems().add(new Transaction("Income", rs.getString("source"), amount, rs.getString("date")));
            }

            rs = stmt.executeQuery("SELECT * FROM Expenses");
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                totalExpenses += amount;
                table.getItems().add(new Transaction("Expense", rs.getString("category"), amount, rs.getString("date")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        double balance = totalIncome - totalExpenses;

        totalIncomeLabel.setText("Total Income: $" + totalIncome);
        totalExpenseLabel.setText("Total Expenses: $" + totalExpenses);
        balanceLabel.setText("Balance Left: $" + balance);

        pieChart.getData().add(new PieChart.Data("Income", totalIncome));
        pieChart.getData().add(new PieChart.Data("Expenses", totalExpenses));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
