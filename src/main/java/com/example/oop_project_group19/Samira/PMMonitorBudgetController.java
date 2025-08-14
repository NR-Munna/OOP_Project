package com.example.oop_project_group19.Samira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PMMonitorBudgetController implements Initializable {

    @FXML private TableColumn<Budget, Float> allocatedColumn;
    @FXML private TextField allocatedTextField;
    @FXML private Button backButton;
    @FXML private TableView<Budget> budgetTable;
    @FXML private Label budgetUtilizationLabel;
    @FXML private Button calculateButton;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private TextField contingencyTextField;
    @FXML private TextField designCostTextField;
    @FXML private TextField developmentCostTextField;
    @FXML private Label errorLabel;
    @FXML private Button exportButton;
    @FXML private TextField miscTextField;
    @FXML private ComboBox<String> projectComboBox;
    @FXML private TableColumn<Budget, String> projectNameColumn;
    @FXML private Button refreshButton;
    @FXML private TableColumn<Budget, Float> remainingColumn;
    @FXML private Button saveButton;
    @FXML private TableColumn<Budget, Float> spentColumn;
    @FXML private TextField spentTextField;
    @FXML private TableColumn<Budget, String> statusBudgetColumn;
    @FXML private TextField testingCostTextField;
    @FXML private TableColumn<Budget, Float> totalBudgetColumn;
    @FXML private TextField totalBudgetTextField;
    @FXML private Label totalProjectBudgetLabel;
    @FXML private Label totalSpentLabel;

    private ObservableList<Budget> budgetList = FXCollections.observableArrayList();
    private Scene scene;
    private int nextBudgetId = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        setupComboBoxes();
        addSampleData();
    }

    private void setupTable() {
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        totalBudgetColumn.setCellValueFactory(new PropertyValueFactory<>("totalBudget"));
        allocatedColumn.setCellValueFactory(new PropertyValueFactory<>("allocated"));
        spentColumn.setCellValueFactory(new PropertyValueFactory<>("spent"));
        remainingColumn.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        statusBudgetColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        budgetTable.setItems(budgetList);
    }

    private void setupComboBoxes() {
        projectComboBox.getItems().addAll("E-commerce Website", "Mobile Banking App", "Data Analytics Dashboard");
        categoryComboBox.getItems().addAll("Development", "Design", "Testing", "Miscellaneous", "Contingency");
    }

    private void addSampleData() {
        budgetList.addAll(
                new Budget(1, "E-commerce Website", 15000f),
                new Budget(2, "Mobile Banking App", 25000f),
                new Budget(3, "Data Analytics Dashboard", 18000f)
        );
        nextBudgetId = 4;
        updateTotals();
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handleCalculate(ActionEvent event) {
        try {
            float development = parseFloat(developmentCostTextField.getText());
            float design = parseFloat(designCostTextField.getText());
            float testing = parseFloat(testingCostTextField.getText());
            float misc = parseFloat(miscTextField.getText());
            float contingency = parseFloat(contingencyTextField.getText());

            float total = development + design + testing + misc + contingency;
            totalBudgetTextField.setText(String.valueOf(total));

            float utilization = 0;
            if (total > 0) {
                utilization = ((development + design + testing + misc) / total) * 100;
            }
            budgetUtilizationLabel.setText(String.format("Budget Utilization: %.1f%%", utilization));

            errorLabel.setText("");
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid number format in cost fields");
        }
    }

    @FXML
    void handleExport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Budget");
        alert.setHeaderText("Budget Report Exported");
        alert.setContentText("Budget report has been exported to budget_report.csv");
        alert.showAndWait();
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        budgetTable.refresh();
        updateTotals();
    }

    @FXML
    void handleSave(ActionEvent event) {
        errorLabel.setText("");

        if (projectComboBox.getValue() == null || totalBudgetTextField.getText().isEmpty()) {
            errorLabel.setText("Please select project and enter total budget");
            return;
        }

        try {
            float totalBudget = Float.parseFloat(totalBudgetTextField.getText());
            float allocated = parseFloat(allocatedTextField.getText());
            float spent = parseFloat(spentTextField.getText());

            Budget budget = new Budget(nextBudgetId++, projectComboBox.getValue(), totalBudget);
            budget.setAllocated(allocated);
            budget.setSpent(spent);
            budget.setRemaining(totalBudget - spent);
            budget.setDevelopmentCost(parseFloat(developmentCostTextField.getText()));
            budget.setDesignCost(parseFloat(designCostTextField.getText()));
            budget.setTestingCost(parseFloat(testingCostTextField.getText()));
            budget.setMiscCost(parseFloat(miscTextField.getText()));
            budget.setContingency(parseFloat(contingencyTextField.getText()));

            budgetList.add(budget);
            updateTotals();
            clearFields();
            errorLabel.setText("Budget saved successfully!");

        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid number format");
        }
    }

    private float parseFloat(String text) {
        return text.isEmpty() ? 0f : Float.parseFloat(text);
    }

    private void clearFields() {
        projectComboBox.setValue(null);
        totalBudgetTextField.clear();
        allocatedTextField.clear();
        spentTextField.clear();
        developmentCostTextField.clear();
        designCostTextField.clear();
        testingCostTextField.clear();
        miscTextField.clear();
        contingencyTextField.clear();
    }

    private void updateTotals() {
        float totalBudget = 0;
        float totalSpent = 0;

        for (Budget budget : budgetList) {
            totalBudget += budget.getTotalBudget();
            totalSpent += budget.getSpent();
        }

        totalProjectBudgetLabel.setText("Total Project Budget: $" + String.format("%.2f", totalBudget));
        totalSpentLabel.setText("Total Spent: $" + String.format("%.2f", totalSpent));
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
