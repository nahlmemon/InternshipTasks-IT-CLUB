package application;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Item Class
class Item {
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final DoubleProperty price;

    public Item(String name, int quantity, double price) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int value) { quantity.set(value); }
    public IntegerProperty quantityProperty() { return quantity; }

    public double getPrice() { return price.get(); }
    public void setPrice(double value) { price.set(value); }
    public DoubleProperty priceProperty() { return price; }
}

// Main Inventory App
public class InventoryApp extends Application {
    private TableView<Item> table;
    private ObservableList<Item> inventory;
    private TextField nameField, quantityField, priceField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory Management");

        // Table setup
        table = new TableView<>();
        inventory = FXCollections.observableArrayList();
        table.setItems(inventory);
        
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Item, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());

        TableColumn<Item, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(data -> data.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, quantityCol, priceCol);
        
        // Input Fields
        nameField = new TextField();
        nameField.setPromptText("Item Name");

        quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        priceField = new TextField();
        priceField.setPromptText("Price");

        // Buttons
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        addButton.setOnAction(e -> addItem());

        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white;");
        updateButton.setOnAction(e -> updateItem());

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #DC3545; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteItem());

        // Layout
        HBox inputBox = new HBox(10, nameField, quantityField, priceField, addButton, updateButton, deleteButton);
        inputBox.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(10, table, inputBox);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to right, #87CEFA, #6a5acd);");

        // Scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItem() {
        try {
            String name = nameField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (name.isEmpty()) {
                showAlert("Invalid Input", "Item name cannot be empty.");
                return;
            }

            inventory.add(new Item(name, quantity, price));
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for Quantity and Price.");
        }
    }

    private void updateItem() {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                String name = nameField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                if (name.isEmpty()) {
                    showAlert("Invalid Input", "Item name cannot be empty.");
                    return;
                }

                selectedItem.setName(name);
                selectedItem.setQuantity(quantity);
                selectedItem.setPrice(price);
                table.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter valid numeric values for Quantity and Price.");
            }
        } else {
            showAlert("No Selection", "Please select an item to update.");
        }
    }

    private void deleteItem() {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            inventory.remove(selectedItem);
        } else {
            showAlert("No Selection", "Please select an item to delete.");
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
