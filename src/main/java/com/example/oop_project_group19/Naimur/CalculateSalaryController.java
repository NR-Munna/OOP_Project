package com.example.oop_project_group19.Naimur;

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

public class CalculateSalaryController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TextField baseSalaryField;

    @FXML
    private TableColumn<SalaryRecord, Float> bonusColumn;

    @FXML
    private TextField bonusField;

    @FXML
    private Button calculateBtn;

    @FXML
    private TableColumn<SalaryRecord, Float> deductionsColumn;

    @FXML
    private TextField deductionsField;

    @FXML
    private TableColumn<SalaryRecord, String> employeeColumn;

    @FXML
    private TableColumn<SalaryRecord, Float> hoursColumn;

    @FXML
    private TextField hoursWorkedField;

    @FXML
    private TextField overtimeField;

    @FXML
    private TableView<SalaryRecord> pendingSalaryTable;

    @FXML
    private TableColumn<SalaryRecord, String> positionColumn;

    @FXML
    private TableColumn<SalaryRecord, Float> rateColumn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Label selectedEmployeeLabel;

    @FXML
    private Button sendToFinanceBtn;

    @FXML
    private TableColumn<SalaryRecord, String> statusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Label totalAmountLabel;

    private Scene scene;
    private ObservableList<SalaryRecord> salaryRecords;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        loadSalaryRecords();
        setupTableSelectionListener();
        clearCalculationFields();
        clearSelection();
    }

    private void initializeTable() {
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        bonusColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        deductionsColumn.setCellValueFactory(new PropertyValueFactory<>("deductions"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadSalaryRecords() {
        salaryRecords = FXCollections.observableArrayList(
                new SalaryRecord("Abel Tesfaye", "HR Manager", 28.5f, 160.0f, 500.0f, 200.0f, "Pending"),
                new SalaryRecord("Jordan Carter", "Senior Developer", 45.0f, 168.0f, 800.0f, 350.0f, "Pending"),
                new SalaryRecord("Charlie Puth", "Project Manager", 38.0f, 165.0f, 600.0f, 250.0f, "Pending"),
                new SalaryRecord("Rakim Williams", "Finance Officer", 32.0f, 162.0f, 400.0f, 180.0f, "Pending"),
                new SalaryRecord("Atif Aslam", "Tech Support Lead", 30.0f, 158.0f, 350.0f, 150.0f, "Pending"),
                new SalaryRecord("Sonu Nigam", "UI Designer", 35.0f, 160.0f, 450.0f, 200.0f, "Calculated"),
                new SalaryRecord("Emma Davis", "QA Tester", 28.0f, 155.0f, 300.0f, 120.0f, "Sent to Finance")
        );
        pendingSalaryTable.setItems(salaryRecords);
    }

    private void setupTableSelectionListener() {
        pendingSalaryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEmployeeLabel.setText("Selected: " + newSelection.getEmployee());
                loadSalaryData(newSelection);
                statusLabel.setText("Status: " + newSelection.getStatus());

                calculateBtn.setDisable(false);
                sendToFinanceBtn.setDisable(!newSelection.getStatus().equals("Calculated"));
            } else {
                clearSelection();
            }
        });
    }

    private void loadSalaryData(SalaryRecord record) {
        baseSalaryField.setText(String.valueOf(record.getRate()));
        hoursWorkedField.setText(String.valueOf(record.getHours()));
        bonusField.setText(String.valueOf(record.getBonus()));
        deductionsField.setText(String.valueOf(record.getDeductions()));
        overtimeField.setText("0.0");

        if (record.getStatus().equals("Calculated") || record.getStatus().equals("Sent to Finance")) {
            float total = calculateTotal(record.getRate(), record.getHours(), record.getBonus(), record.getDeductions(), 0.0f);
            totalAmountLabel.setText(String.format("Total Amount: $%.2f", total));
        } else {
            totalAmountLabel.setText("Total Amount: Not calculated");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void handleCalculate(ActionEvent event) {
        SalaryRecord selectedRecord = pendingSalaryTable.getSelectionModel().getSelectedItem();
        if (selectedRecord == null) {
            showAlert("Please select an employee to calculate salary", Alert.AlertType.WARNING);
            return;
        }

        if (selectedRecord.getStatus().equals("Sent to Finance")) {
            showAlert("This salary has already been sent to Finance", Alert.AlertType.WARNING);
            return;
        }

        if (validateInput()) {
            float rate = Float.parseFloat(baseSalaryField.getText().trim());
            float hours = Float.parseFloat(hoursWorkedField.getText().trim());
            float bonus = Float.parseFloat(bonusField.getText().trim());
            float deductions = Float.parseFloat(deductionsField.getText().trim());
            float overtime = Float.parseFloat(overtimeField.getText().trim());

            float total = calculateTotal(rate, hours, bonus, deductions, overtime);

            selectedRecord.setRate(rate);
            selectedRecord.setHours(hours + overtime);
            selectedRecord.setBonus(bonus);
            selectedRecord.setDeductions(deductions);
            selectedRecord.setStatus("Calculated");

            totalAmountLabel.setText(String.format("Total Amount: $%.2f", total));
            statusLabel.setText("Status: Calculated");
            sendToFinanceBtn.setDisable(false);

            pendingSalaryTable.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Salary Calculated");
            alert.setHeaderText("Success");
            alert.setContentText(String.format("Salary calculated for %s: $%.2f", selectedRecord.getEmployee(), total));
            alert.showAndWait();
        }
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        pendingSalaryTable.setItems(salaryRecords);
        clearSelection();
        clearCalculationFields();
    }

    @FXML
    void handleSendToFinance(ActionEvent event) {
        SalaryRecord selectedRecord = pendingSalaryTable.getSelectionModel().getSelectedItem();
        if (selectedRecord == null) {
            showAlert("Please select an employee record", Alert.AlertType.WARNING);
            return;
        }

        if (!selectedRecord.getStatus().equals("Calculated")) {
            showAlert("Please calculate the salary first before sending to Finance", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Send to Finance");
        confirmAlert.setHeaderText("Confirm Action");
        confirmAlert.setContentText("Are you sure you want to send this salary calculation to the Finance Officer?");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedRecord.setStatus("Sent to Finance");
            statusLabel.setText("Status: Sent to Finance");
            sendToFinanceBtn.setDisable(true);

            pendingSalaryTable.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sent to Finance");
            alert.setHeaderText("Success");
            alert.setContentText("Salary details for " + selectedRecord.getEmployee() + " have been sent to the Finance Officer.");
            alert.showAndWait();
        }
    }

    private float calculateTotal(float rate, float hours, float bonus, float deductions, float overtime) {
        float regularPay = rate * hours;
        float overtimePay = (rate * 1.5f) * overtime;
        return regularPay + overtimePay + bonus - deductions;
    }

    private boolean validateInput() {
        try {
            float rate = Float.parseFloat(baseSalaryField.getText().trim());
            float hours = Float.parseFloat(hoursWorkedField.getText().trim());
            float bonus = Float.parseFloat(bonusField.getText().trim());
            float deductions = Float.parseFloat(deductionsField.getText().trim());
            float overtime = Float.parseFloat(overtimeField.getText().trim());

            if (rate < 0 || hours < 0 || bonus < 0 || deductions < 0 || overtime < 0) {
                showAlert("Values cannot be negative", Alert.AlertType.ERROR);
                return false;
            }

            if (hours > 200) {
                showAlert("Hours worked cannot exceed 200", Alert.AlertType.ERROR);
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values", Alert.AlertType.ERROR);
            return false;
        }
    }

    private void clearSelection() {
        selectedEmployeeLabel.setText("Selected: None");
        statusLabel.setText("Status: None");
        totalAmountLabel.setText("Total Amount: $0.00");
        calculateBtn.setDisable(true);
        sendToFinanceBtn.setDisable(true);
    }

    private void clearCalculationFields() {
        baseSalaryField.clear();
        hoursWorkedField.clear();
        bonusField.clear();
        deductionsField.clear();
        overtimeField.clear();
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