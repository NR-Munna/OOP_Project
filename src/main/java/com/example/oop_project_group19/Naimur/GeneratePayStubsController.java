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
import java.time.format.DateTimeFormatter;

public class GeneratePayStubsController {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<FinanceFreelancer, String> emailColumn;

    @FXML
    private TableColumn<FinanceFreelancer, String> freelancerIdColumn;

    @FXML
    private TableColumn<FinanceFreelancer, String> freelancerNameColumn;

    @FXML
    private TableView<FinanceFreelancer> freelancerTable;

    @FXML
    private Button generatePayStubBtn;

    @FXML
    private TextField hourlyRateField;

    @FXML
    private TextArea payStubDisplayArea;

    @FXML
    private TextField paymentAmountField;

    @FXML
    private DatePicker paymentDatePicker;

    @FXML
    private TextField projectNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField selectedFreelancerField;

    @FXML
    private TableColumn<FinanceFreelancer, String> skillsColumn;

    @FXML
    private TextField workHoursField;

    private Scene scene;
    private ObservableList<FinanceFreelancer> freelancers;

    @FXML
    public void initialize() {
        initializeTable();
        loadFreelancers();
        setupTableSelectionListener();
        clearFields();
        payStubDisplayArea.setEditable(false);
        paymentAmountField.setEditable(false);
        selectedFreelancerField.setEditable(false);
    }

    private void initializeTable() {
        freelancerIdColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerId"));
        freelancerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
    }

    private void loadFreelancers() {
        freelancers = FXCollections.observableArrayList(
                new FinanceFreelancer("FL-001", "John Doe", "john.doe@email.com", "Java, Spring Boot, MySQL"),
                new FinanceFreelancer("FL-002", "Jane Smith", "jane.smith@email.com", "React, Node.js, MongoDB"),
                new FinanceFreelancer("FL-003", "Mike Johnson", "mike.johnson@email.com", "Python, Django, PostgreSQL"),
                new FinanceFreelancer("FL-004", "Sarah Wilson", "sarah.wilson@email.com", "UI/UX Design, Figma, Adobe XD"),
                new FinanceFreelancer("FL-005", "David Brown", "david.brown@email.com", "PHP, Laravel, Vue.js"),
                new FinanceFreelancer("FL-006", "Emma Davis", "emma.davis@email.com", "Android Development, Kotlin"),
                new FinanceFreelancer("FL-007", "Alex Turner", "alex.turner@email.com", "Data Science, Machine Learning")
        );
        freelancerTable.setItems(freelancers);
    }

    private void setupTableSelectionListener() {
        freelancerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedFreelancerField.setText(newSelection.getName());
                generatePayStubBtn.setDisable(false);
            } else {
                selectedFreelancerField.clear();
                generatePayStubBtn.setDisable(true);
            }
        });
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onGeneratePayStub(ActionEvent event) {
        FinanceFreelancer selectedFreelancer = freelancerTable.getSelectionModel().getSelectedItem();
        if (selectedFreelancer == null) {
            showAlert("Please select a freelancer", Alert.AlertType.WARNING);
            return;
        }

        if (validatePaymentDetails()) {
            try {
                float hourlyRate = Float.parseFloat(hourlyRateField.getText().trim());
                float workHours = Float.parseFloat(workHoursField.getText().trim());
                String projectName = projectNameField.getText().trim();
                LocalDate paymentDate = paymentDatePicker.getValue();

                float totalAmount = hourlyRate * workHours;
                paymentAmountField.setText(String.format("%.2f", totalAmount));

                String payStub = generatePayStubText(selectedFreelancer, hourlyRate, workHours, totalAmount, projectName, paymentDate);
                payStubDisplayArea.setText(payStub);
                saveBtn.setDisable(false);

            } catch (NumberFormatException e) {
                showAlert("Please enter valid numeric values for rate and hours", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        if (payStubDisplayArea.getText().isEmpty()) {
            showAlert("Please generate a pay stub first", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pay Stub Saved");
        alert.setHeaderText("Success");
        alert.setContentText("Pay stub has been saved as PDF successfully.\nNote: In a real application, this would generate an actual PDF file.");
        alert.showAndWait();

        clearFields();
        payStubDisplayArea.clear();
        saveBtn.setDisable(true);
    }

    private String generatePayStubText(FinanceFreelancer freelancer, float hourlyRate, float workHours, float totalAmount, String projectName, LocalDate paymentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        return String.format(
                "═══════════════════════════════════════════════════════════════\n" +
                        "                           PAY STUB\n" +
                        "                    FREELANCING COMPANY\n" +
                        "═══════════════════════════════════════════════════════════════\n\n" +
                        "FREELANCER INFORMATION:\n" +
                        "  Name: %s\n" +
                        "  ID: %s\n" +
                        "  Email: %s\n" +
                        "  Skills: %s\n\n" +
                        "PAYMENT DETAILS:\n" +
                        "  Project Name: %s\n" +
                        "  Payment Date: %s\n" +
                        "  Hourly Rate: $%.2f\n" +
                        "  Hours Worked: %.2f\n" +
                        "  Total Payment: $%.2f\n\n" +
                        "DEDUCTIONS:\n" +
                        "  Tax Withholding (10%%): $%.2f\n" +
                        "  Service Fee (5%%): $%.2f\n" +
                        "  Net Payment: $%.2f\n\n" +
                        "═══════════════════════════════════════════════════════════════\n" +
                        "This pay stub is generated electronically and is valid without signature.\n" +
                        "For any queries, please contact: finance@freelancingcompany.com\n" +
                        "═══════════════════════════════════════════════════════════════",
                freelancer.getName(), freelancer.getFreelancerId(), freelancer.getEmail(), freelancer.getSkills(),
                projectName, paymentDate.format(formatter), hourlyRate, workHours, totalAmount,
                totalAmount * 0.10f, totalAmount * 0.05f, totalAmount * 0.85f
        );
    }

    private boolean validatePaymentDetails() {
        if (hourlyRateField.getText().trim().isEmpty()) {
            showAlert("Hourly rate is required", Alert.AlertType.ERROR);
            return false;
        }

        if (workHoursField.getText().trim().isEmpty()) {
            showAlert("Work hours is required", Alert.AlertType.ERROR);
            return false;
        }

        if (projectNameField.getText().trim().isEmpty()) {
            showAlert("Project name is required", Alert.AlertType.ERROR);
            return false;
        }

        if (paymentDatePicker.getValue() == null) {
            showAlert("Payment date is required", Alert.AlertType.ERROR);
            return false;
        }

        try {
            float hourlyRate = Float.parseFloat(hourlyRateField.getText().trim());
            float workHours = Float.parseFloat(workHoursField.getText().trim());

            if (hourlyRate <= 0) {
                showAlert("Hourly rate must be greater than 0", Alert.AlertType.ERROR);
                return false;
            }

            if (workHours <= 0) {
                showAlert("Work hours must be greater than 0", Alert.AlertType.ERROR);
                return false;
            }

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void clearFields() {
        hourlyRateField.clear();
        workHoursField.clear();
        projectNameField.clear();
        paymentDatePicker.setValue(null);
        paymentAmountField.clear();
        selectedFreelancerField.clear();
        generatePayStubBtn.setDisable(true);
        saveBtn.setDisable(true);
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