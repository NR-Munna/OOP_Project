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

public class CreateBudgetController {

    @FXML
    private Button addCategoryBtn;

    @FXML
    private TableColumn<BudgetCategory, Float> allocatedAmountColumn;

    @FXML
    private TextField allocatedAmountField;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<BudgetCategory> budgetCategoriesTable;

    @FXML
    private TextArea budgetDescriptionArea;

    @FXML
    private DatePicker budgetEndDatePicker;

    @FXML
    private DatePicker budgetStartDatePicker;

    @FXML
    private TextField categoryField;

    @FXML
    private TableColumn<BudgetCategory, String> categoryNameColumn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button createBudgetPlanBtn;

    @FXML
    private ComboBox<String> departmentCombo;

    @FXML
    private TableColumn<BudgetCategory, Float> remainingAmountColumn;

    @FXML
    private Button saveBudgetBtn;

    @FXML
    private TextField selectedDepartmentField;

    @FXML
    private TextField totalBudgetField;

    private Scene scene;
    private ObservableList<BudgetCategory> budgetCategories;

    @FXML
    public void initialize() {
        initializeTable();
        initializeDepartmentCombo();
        budgetCategories = FXCollections.observableArrayList();
        budgetCategoriesTable.setItems(budgetCategories);
        selectedDepartmentField.setEditable(false);
        totalBudgetField.setEditable(false);
        saveBudgetBtn.setDisable(true);
        updateTotalBudget();
    }

    private void initializeTable() {
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        allocatedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("allocatedAmount"));
        remainingAmountColumn.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
    }

    private void initializeDepartmentCombo() {
        ObservableList<String> departments = FXCollections.observableArrayList(
                "Human Resources", "Finance", "Technical Support", "Project Management",
                "Development", "Marketing", "Sales", "Operations", "Legal", "IT Infrastructure"
        );
        departmentCombo.setItems(departments);

        departmentCombo.setOnAction(event -> {
            String selectedDept = departmentCombo.getValue();
            if (selectedDept != null) {
                selectedDepartmentField.setText(selectedDept);
                createBudgetPlanBtn.setDisable(false);
            }
        });
    }

    @FXML
    void onAddCategory(ActionEvent event) {
        if (validateCategoryInput()) {
            try {
                String categoryName = categoryField.getText().trim();
                float allocatedAmount = Float.parseFloat(allocatedAmountField.getText().trim());

                for (BudgetCategory category : budgetCategories) {
                    if (category.getCategoryName().equalsIgnoreCase(categoryName)) {
                        showAlert("Category already exists. Please enter a different category name.", Alert.AlertType.WARNING);
                        return;
                    }
                }

                BudgetCategory newCategory = new BudgetCategory(categoryName, allocatedAmount, allocatedAmount);
                budgetCategories.add(newCategory);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Category Added");
                alert.setHeaderText("Success");
                alert.setContentText(String.format("Budget category '%s' with amount $%.2f has been added.", categoryName, allocatedAmount));
                alert.showAndWait();

                clearCategoryFields();
                updateTotalBudget();

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
    void onClear(ActionEvent event) {
        clearAllFields();
    }

    @FXML
    void onCreateBudgetPlan(ActionEvent event) {
        if (departmentCombo.getValue() == null) {
            showAlert("Please select a department first", Alert.AlertType.WARNING);
            return;
        }

        if (budgetStartDatePicker.getValue() == null || budgetEndDatePicker.getValue() == null) {
            showAlert("Please select budget start and end dates", Alert.AlertType.WARNING);
            return;
        }

        if (budgetStartDatePicker.getValue().isAfter(budgetEndDatePicker.getValue())) {
            showAlert("Start date cannot be after end date", Alert.AlertType.ERROR);
            return;
        }

        saveBudgetBtn.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Budget Plan Created");
        alert.setHeaderText("Success");
        alert.setContentText("Budget plan has been created successfully. You can now add categories or save the budget.");
        alert.showAndWait();
    }

    @FXML
    void onSaveBudget(ActionEvent event) {
        if (budgetCategories.isEmpty()) {
            showAlert("Please add at least one budget category before saving", Alert.AlertType.WARNING);
            return;
        }

        if (departmentCombo.getValue() == null) {
            showAlert("Please select a department", Alert.AlertType.WARNING);
            return;
        }

        if (budgetStartDatePicker.getValue() == null || budgetEndDatePicker.getValue() == null) {
            showAlert("Please select budget period dates", Alert.AlertType.WARNING);
            return;
        }

        String department = departmentCombo.getValue();
        LocalDate startDate = budgetStartDatePicker.getValue();
        LocalDate endDate = budgetEndDatePicker.getValue();
        String description = budgetDescriptionArea.getText().trim();
        float totalBudget = Float.parseFloat(totalBudgetField.getText().replace("$", "").replace(",", ""));

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Save Budget Plan");
        confirmAlert.setHeaderText("Confirm Budget Plan");
        confirmAlert.setContentText(String.format(
                "Department: %s\nBudget Period: %s to %s\nTotal Budget: $%.2f\nCategories: %d\n\nSave this budget plan?",
                department, startDate, endDate, totalBudget, budgetCategories.size()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Budget Saved");
            successAlert.setHeaderText("Success");
            successAlert.setContentText(String.format("Budget plan for %s department has been saved successfully!\nTotal Budget: $%.2f", department, totalBudget));
            successAlert.showAndWait();

            clearAllFields();
        }
    }

    private boolean validateCategoryInput() {
        if (categoryField.getText().trim().isEmpty()) {
            showAlert("Please enter a category name", Alert.AlertType.ERROR);
            return false;
        }

        if (allocatedAmountField.getText().trim().isEmpty()) {
            showAlert("Please enter an allocated amount", Alert.AlertType.ERROR);
            return false;
        }

        try {
            float amount = Float.parseFloat(allocatedAmountField.getText().trim());
            if (amount <= 0) {
                showAlert("Allocated amount must be greater than 0", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid numeric amount", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void updateTotalBudget() {
        float total = 0.0f;
        for (BudgetCategory category : budgetCategories) {
            total += category.getAllocatedAmount();
        }
        totalBudgetField.setText(String.format("$%.2f", total));
    }

    private void clearCategoryFields() {
        categoryField.clear();
        allocatedAmountField.clear();
    }

    private void clearAllFields() {
        departmentCombo.setValue(null);
        selectedDepartmentField.clear();
        budgetStartDatePicker.setValue(null);
        budgetEndDatePicker.setValue(null);
        budgetDescriptionArea.clear();
        categoryField.clear();
        allocatedAmountField.clear();
        totalBudgetField.clear();
        budgetCategories.clear();
        createBudgetPlanBtn.setDisable(true);
        saveBudgetBtn.setDisable(true);
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