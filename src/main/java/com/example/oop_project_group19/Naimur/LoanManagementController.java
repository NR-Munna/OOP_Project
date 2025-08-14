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

public class LoanManagementController {

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<PaymentDelay, String> clientNameColumn;

    @FXML
    private TableColumn<PaymentDelay, String> delayIdColumn;

    @FXML
    private TableColumn<PaymentDelay, String> delayStatusColumn;

    @FXML
    private TableColumn<PaymentDelay, Float> delayedAmountColumn;

    @FXML
    private Button denyBtn;

    @FXML
    private TableColumn<LoanRequest, String> freelancerNameColumn;

    @FXML
    private TableColumn<LoanRequest, Float> loanAmountColumn;

    @FXML
    private TableColumn<LoanRequest, String> loanRequestIdColumn;

    @FXML
    private TableView<LoanRequest> loanRequestsTable;

    @FXML
    private TableColumn<LoanRequest, String> loanStatusColumn;

    @FXML
    private TableColumn<PaymentDelay, LocalDate> originalDueDateColumn;

    @FXML
    private TableView<PaymentDelay> paymentDelaysTable;

    @FXML
    private TableColumn<PaymentDelay, String> projectNameColumn;

    @FXML
    private TableColumn<LoanRequest, LocalDate> requestDateColumn;

    @FXML
    private TextArea requestReasonArea;

    @FXML
    private TextField selectedAmountField;

    @FXML
    private TextField selectedApplicantField;

    @FXML
    private TextField selectedRequestIdField;

    @FXML
    private TableColumn<LoanRequest, String> urgencyColumn;

    private Scene scene;
    private ObservableList<LoanRequest> loanRequests;
    private ObservableList<PaymentDelay> paymentDelays;
    private String currentSelectedType = ""; // "loan" or "delay"

    @FXML
    public void initialize() {
        initializeTables();
        loadLoanRequests();
        loadPaymentDelays();
        setupTableSelectionListeners();
        clearSelection();
        setFieldsEditable(false);
    }

    private void initializeTables() {
        loanRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        freelancerNameColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerName"));
        loanAmountColumn.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
        requestDateColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        loanStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        delayIdColumn.setCellValueFactory(new PropertyValueFactory<>("delayId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        delayedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("delayedAmount"));
        originalDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("originalDueDate"));
        delayStatusColumn.setCellValueFactory(new PropertyValueFactory<>("delayStatus"));
    }

    private void loadLoanRequests() {
        loanRequests = FXCollections.observableArrayList(
                new LoanRequest("LR-001", "John Doe", 5000.0f, LocalDate.of(2024, 3, 10), "High", "Pending", "Medical emergency - need funds for treatment"),
                new LoanRequest("LR-002", "Jane Smith", 3000.0f, LocalDate.of(2024, 3, 12), "Medium", "Pending", "Equipment purchase for new project"),
                new LoanRequest("LR-003", "Mike Johnson", 2500.0f, LocalDate.of(2024, 3, 15), "Low", "Pending", "Professional certification course fees"),
                new LoanRequest("LR-004", "Sarah Wilson", 7500.0f, LocalDate.of(2024, 3, 18), "High", "Pending", "Home renovation for home office setup"),
                new LoanRequest("LR-005", "David Brown", 4000.0f, LocalDate.of(2024, 3, 20), "Medium", "Pending", "Car repair for client meetings")

        );
        loanRequestsTable.setItems(loanRequests);
    }

    private void loadPaymentDelays() {
        paymentDelays = FXCollections.observableArrayList(
                new PaymentDelay("PD-001", "Tech Solutions Inc", "E-commerce Platform", 15000.0f, LocalDate.of(2024, 3, 15), "Under Review"),
                new PaymentDelay("PD-002", "Digital Marketing Co", "Social Media Campaign", 8500.0f, LocalDate.of(2024, 3, 20), "Under Review"),
                new PaymentDelay("PD-003", "StartUp Ventures", "Mobile App Development", 12000.0f, LocalDate.of(2024, 3, 25), "Under Review"),
                new PaymentDelay("PD-004", "Global Systems", "Database Migration", 22000.0f, LocalDate.of(2024, 3, 30), "Under Review"),
                new PaymentDelay("PD-005", "Innovation Labs", "AI Integration", 9800.0f, LocalDate.of(2024, 4, 5), "Resolved"),
                new PaymentDelay("PD-006", "Creative Agency", "Website Redesign", 6500.0f, LocalDate.of(2024, 4, 10), "Extended")
        );
        paymentDelaysTable.setItems(paymentDelays);
    }

    private void setupTableSelectionListeners() {
        loanRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                paymentDelaysTable.getSelectionModel().clearSelection();
                loadLoanRequestDetails(newSelection);
                currentSelectedType = "loan";
            }
        });

        paymentDelaysTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loanRequestsTable.getSelectionModel().clearSelection();
                loadPaymentDelayDetails(newSelection);
                currentSelectedType = "delay";
            }
        });
    }

    private void loadLoanRequestDetails(LoanRequest request) {
        selectedRequestIdField.setText(request.getRequestId());
        selectedApplicantField.setText(request.getFreelancerName());
        selectedAmountField.setText(String.format("%.2f", request.getLoanAmount()));
        requestReasonArea.setText(request.getReason());

        setFieldsEditable(true);
        updateButtonStates(request.getStatus());
    }

    private void loadPaymentDelayDetails(PaymentDelay delay) {
        selectedRequestIdField.setText(delay.getDelayId());
        selectedApplicantField.setText(delay.getClientName());
        selectedAmountField.setText(String.format("%.2f", delay.getDelayedAmount()));
        requestReasonArea.setText("Payment delay for project: " + delay.getProjectName() + "\nOriginal due date: " + delay.getOriginalDueDate());

        setFieldsEditable(true);
        updateButtonStates(delay.getDelayStatus());
    }

    private void updateButtonStates(String status) {
        boolean isPending = status.equals("Pending") || status.equals("Under Review");
        approveBtn.setDisable(!isPending);
        denyBtn.setDisable(!isPending);
    }

    @FXML
    void onApprove(ActionEvent event) {
        if (currentSelectedType.equals("loan")) {
            approveLoanRequest();
        } else if (currentSelectedType.equals("delay")) {
            approvePaymentDelay();
        } else {
            showAlert("Please select a loan request or payment delay first", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onDeny(ActionEvent event) {
        if (currentSelectedType.equals("loan")) {
            denyLoanRequest();
        } else if (currentSelectedType.equals("delay")) {
            denyPaymentDelay();
        } else {
            showAlert("Please select a loan request or payment delay first", Alert.AlertType.WARNING);
        }
    }

    private void approveLoanRequest() {
        LoanRequest selectedRequest = loanRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) return;

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Approve Loan Request");
        confirmAlert.setHeaderText("Confirm Loan Approval");
        confirmAlert.setContentText(String.format(
                "Approve loan request for %s?\nAmount: $%.2f\nUrgency: %s\nReason: %s",
                selectedRequest.getFreelancerName(), selectedRequest.getLoanAmount(),
                selectedRequest.getUrgency(), selectedRequest.getReason()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedRequest.setStatus("Approved");
            loanRequestsTable.refresh();

            showAlert(String.format("Loan request %s has been approved for $%.2f",
                    selectedRequest.getRequestId(), selectedRequest.getLoanAmount()), Alert.AlertType.INFORMATION);

            clearSelection();
        }
    }

    private void denyLoanRequest() {
        LoanRequest selectedRequest = loanRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) return;

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Deny Loan Request");
        confirmAlert.setHeaderText("Confirm Loan Denial");
        confirmAlert.setContentText(String.format("Deny loan request for %s (Amount: $%.2f)?",
                selectedRequest.getFreelancerName(), selectedRequest.getLoanAmount()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedRequest.setStatus("Denied");
            loanRequestsTable.refresh();

            showAlert(String.format("Loan request %s has been denied", selectedRequest.getRequestId()), Alert.AlertType.INFORMATION);

            clearSelection();
        }
    }

    private void approvePaymentDelay() {
        PaymentDelay selectedDelay = paymentDelaysTable.getSelectionModel().getSelectedItem();
        if (selectedDelay == null) return;

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Approve Payment Extension");
        confirmAlert.setHeaderText("Confirm Payment Extension");
        confirmAlert.setContentText(String.format("Approve payment extension for %s?\nProject: %s\nAmount: $%.2f",
                selectedDelay.getClientName(), selectedDelay.getProjectName(), selectedDelay.getDelayedAmount()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedDelay.setDelayStatus("Extended");
            paymentDelaysTable.refresh();

            showAlert(String.format("Payment delay %s has been approved", selectedDelay.getDelayId()), Alert.AlertType.INFORMATION);

            clearSelection();
        }
    }

    private void denyPaymentDelay() {
        PaymentDelay selectedDelay = paymentDelaysTable.getSelectionModel().getSelectedItem();
        if (selectedDelay == null) return;

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Deny Payment Extension");
        confirmAlert.setHeaderText("Confirm Payment Extension Denial");
        confirmAlert.setContentText(String.format("Deny payment extension for %s?\nOriginal payment is required by due date.",
                selectedDelay.getClientName()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedDelay.setDelayStatus("Denied");
            paymentDelaysTable.refresh();

            showAlert(String.format("Payment delay %s has been denied", selectedDelay.getDelayId()), Alert.AlertType.INFORMATION);

            clearSelection();
        }
    }

    private void clearSelection() {
        selectedRequestIdField.clear();
        selectedApplicantField.clear();
        selectedAmountField.clear();
        requestReasonArea.clear();
        currentSelectedType = "";
        setFieldsEditable(false);
        approveBtn.setDisable(true);
        denyBtn.setDisable(true);
    }

    private void setFieldsEditable(boolean editable) {
        selectedRequestIdField.setEditable(false);
        selectedApplicantField.setEditable(false);
        selectedAmountField.setEditable(false);
        requestReasonArea.setEditable(false);
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