package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cool JavaFX GUI");

        // Label
        Label label = new Label("Welcome!");
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff;");

        // TextField
        TextField textField = new TextField();
        textField.setPromptText("Enter your name...");
        textField.setStyle("-fx-font-size: 14px; -fx-border-color: #6a5acd; -fx-border-radius: 5px;");

        // Enter Button
        Button button = new Button("Enter");
        button.setStyle("-fx-background-color: #6a5acd; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #483d8b; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #6a5acd; -fx-text-fill: white;"));
        button.setOnAction(e -> label.setText("Hello, " + textField.getText() + "!"));

        // Layout
        VBox layout = new VBox(15, label, textField, button);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to right, #87CEFA, #6a5acd); -fx-border-radius: 10px;");

        // Scene
        Scene scene = new Scene(layout, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
