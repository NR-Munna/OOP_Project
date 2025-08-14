package com.example.oop_project_group19.Naimur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrackExpensesController {

    @FXML
    private Button addExpenseBtn;

    @FXML
    private TableColumn<Expense, Float> amountColumn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<ExpenseCategory, String> categoryColumn;

    @FXML
    private TableView<ExpenseCategory> categoryTotalsTable;

    @FXML
    private TableColumn<Expense, LocalDate> dateColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TextField expenseAmountField;

    @FXML
    private ComboBox<String> expenseCategoryCombo;

    @FXML
    private DatePicker expenseDatePicker;

    @FXML
    private TextField expenseDescriptionField;

    @FXML
    private TableView<Expense> expenseTable;

    @FXML
    private TableColumn<ExpenseCategory, Float> totalAmountColumn;

    @FXML
    private TableColumn<ExpenseCategory, String> totalCategoryColumn;

    @FXML
    private Button updateTotalsBtn;

    private Scene scene;
    private ObservableList<Expense> expenses;
    private ObservableList<ExpenseCategory> categoryTotals;

    @FXML
    public void initialize() {
        initializeTables();
        initializeComboBox();
        loadExpenses();
        updateCategoryTotals();
    }

    private void initializeTables() {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        totalCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    private void initializeComboBox() {
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Office Supplies", "Software Licenses", "Marketing", "Travel",
                "Equipment", "Utilities", "Professional Services", "Training", "Other"
        );
        expenseCategoryCombo.setItems(categories);
    }

    private void loadExpenses() {
        expenses = FXCollections.observableArrayList(
                new Expense("Office Supplies", 250.0f, LocalDate.of(2024, 3, 1), "Stationery and paper"),
                new Expense("Software Licenses", 1200.0f, LocalDate.of(2024, 3, 2), "Adobe Creative Suite annual license"),
                new Expense("Marketing", 800.0f, LocalDate.of(2024, 3, 3), "Social media advertising"),
                new Expense("Travel", 450.0f, LocalDate.of(2024, 3, 4), "Client meeting travel expenses"),
                new Expense("Equipment", 1500.0f, LocalDate.of(2024, 3, 5), "New laptops for developers"),
                new Expense("Utilities", 300.0f, LocalDate.of(2024, 3, 6), "Internet and electricity"),
                new Expense("Professional Services", 600.0f, LocalDate.of(2024, 3, 7), "Legal consultation"),
                new Expense("Training", 400.0f, LocalDate.of(2024, 3, 8), "Technical certification courses"),
                new Expense("Office Supplies", 150.0f, LocalDate.of(2024, 3, 9), "Printer cartridges"),
                new Expense("Marketing", 350.0f, LocalDate.of(2024, 3, 10), "Website maintenance")
        );
        expenseTable.setItems(expenses);
    }

    @FXML
    void onAddExpense(ActionEvent event) {
        if (validateExpenseInput()) {
            try {
                String category = expenseCategoryCombo.getValue();
                float amount = Float.parseFloat(expenseAmountField.getText().trim());
                LocalDate date = expenseDatePicker.getValue();
                String description = expenseDescriptionField.getText().trim();

                Expense newExpense = new Expense(category, amount, date, description);
                expenses.add(newExpense);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Expense Added");
                alert.setHeaderText("Success");
                alert.setContentText(String.format("Expense of $%.2f for %s has been added successfully.", amount, category));
                alert.showAndWait();

                clearExpenseFields();
                updateCategoryTotals();

            } catch (NumberFormatException e) {
                showAlert("Please enter a valid amount", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onUpdateTotals(ActionEvent event) {
        updateCategoryTotals();
        showAlert("Category totals have been updated successfully", Alert.AlertType.INFORMATION);
    }

    private void updateCategoryTotals() {
        Map<String, Float> totalsMap = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            float amount = expense.getAmount();
            totalsMap.put(category, totalsMap.getOrDefault(category, 0.0f) + amount);
        }

        categoryTotals = FXCollections.observableArrayList();
        for (Map.Entry<String, Float> entry : totalsMap.entrySet()) {
            categoryTotals.add(new ExpenseCategory(entry.getKey(), entry.getValue()));
        }

        categoryTotalsTable.setItems(categoryTotals);
    }

    private boolean validateExpenseInput() {
        if (expenseCategoryCombo.getValue() == null) {
            showAlert("Please select an expense category", Alert.AlertType.ERROR);
            return false;
        }

        if (expenseAmountField.getText().trim().isEmpty()) {
            showAlert("Please enter an expense amount", Alert.AlertType.ERROR);
            return false;
        }

        if (expenseDatePicker.getValue() == null) {
            showAlert("Please select an expense date", Alert.AlertType.ERROR);
            return false;
        }

        if (expenseDescriptionField.getText().trim().isEmpty()) {
            showAlert("Please enter an expense description", Alert.AlertType.ERROR);
            return false;
        }

        try {
            float amount = Float.parseFloat(expenseAmountField.getText().trim());
            if (amount <= 0) {
                showAlert("Expense amount must be greater than 0", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid numeric amount", Alert.AlertType.ERROR);
            return false;
        }

        if (expenseDatePicker.getValue().isAfter(LocalDate.now())) {
            showAlert("Expense date cannot be in the future", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void clearExpenseFields() {
        expenseCategoryCombo.setValue(null);
        expenseAmountField.clear();
        expenseDatePicker.setValue(null);
        expenseDescriptionField.clear();
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}