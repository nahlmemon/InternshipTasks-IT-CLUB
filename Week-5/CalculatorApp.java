package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    private TextField display = new TextField();
    private String operator = "";
    private double num1 = 0;
    private boolean startNewNumber = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Colorful JavaFX Calculator");

        // Display Styling
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setPrefHeight(60);
        display.setStyle("-fx-font-size: 20px; -fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 5px;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 15px; -fx-border-radius: 10px;");

        String[][] buttonLabels = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", "C", "=", "+"}
        };

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String label = buttonLabels[row][col];
                Button button = new Button(label);
                button.setPrefSize(70, 70);
                button.setStyle(getButtonStyle(label));

                button.setOnMouseEntered(e -> button.setStyle(getHoverStyle(label))); // Hover effect
                button.setOnMouseExited(e -> button.setStyle(getButtonStyle(label))); // Reset style
                
                button.setOnAction(e -> handleButtonClick(label));
                grid.add(button, col, row + 1);
            }
        }

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(display, 0, 0);
        root.add(grid, 0, 1);

        Scene scene = new Scene(root, 330, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getButtonStyle(String label) {
        if (label.matches("[0-9]")) return "-fx-background-color: lightblue; -fx-text-fill: black; -fx-font-size: 18px;";
        if (label.matches("[+\\-*/]")) return "-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 20px;";
        if (label.equals("C")) return "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 18px;";
        if (label.equals("=")) return "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 20px;";
        return "-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 18px;";
    }

    private String getHoverStyle(String label) {
        if (label.matches("[0-9]")) return "-fx-background-color: deepskyblue; -fx-text-fill: black; -fx-font-size: 18px;";
        if (label.matches("[+\\-*/]")) return "-fx-background-color: darkorange; -fx-text-fill: white; -fx-font-size: 20px;";
        if (label.equals("C")) return "-fx-background-color: darkred; -fx-text-fill: white; -fx-font-size: 18px;";
        if (label.equals("=")) return "-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 20px;";
        return "-fx-background-color: darkgray; -fx-text-fill: white; -fx-font-size: 18px;";
    }

    private void handleButtonClick(String label) {
        if (label.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(label);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + label);
            }
        } else if (label.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = label;
            startNewNumber = true;
        } else if (label.equals("=")) {
            double num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if (label.equals("C")) {
            display.setText("");
            num1 = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double num1, double num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> (num2 != 0) ? num1 / num2 : 0;
            default -> num2;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
