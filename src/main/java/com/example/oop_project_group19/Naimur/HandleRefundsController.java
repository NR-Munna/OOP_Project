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

public class HandleRefundsController {

    @FXML
    private RadioButton approveRadio;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<RefundRequest, String> clientNameColumn;

    @FXML
    private TextField decisionReasonField;

    @FXML
    private RadioButton denyRadio;

    @FXML
    private TableColumn<RefundRequest, String> projectNameColumn;

    @FXML
    private TableColumn<RefundRequest, Float> refundAmountColumn;

    @FXML
    private TextArea refundReasonArea;

    @FXML
    private TableView<RefundRequest> refundRequestsTable;

    @FXML
    private TableColumn<RefundRequest, LocalDate> requestDateColumn;

    @FXML
    private TableColumn<RefundRequest, String> requestIdColumn;

    @FXML
    private TextField selectedClientNameField;

    @FXML
    private TextField selectedProjectField;

    @FXML
    private TextField selectedRefundAmountField;

    @FXML
    private TextField selectedRequestDateField;

    @FXML
    private TextField selectedRequestIdField;

    @FXML
    private TableColumn<RefundRequest, String> statusColumn;

    @FXML
    private Button updateStatusBtn;

    private Scene scene;
    private ObservableList<RefundRequest> refundRequests;
    private ToggleGroup decisionGroup;

    @FXML
    public void initialize() {
        initializeTable();
        initializeRadioButtons();
        loadRefundRequests();
        setupTableSelectionListener();
        clearSelection();
        setFieldsEditable(false);
    }

    private void initializeTable() {
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        refundAmountColumn.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));
        requestDateColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void initializeRadioButtons() {
        decisionGroup = new ToggleGroup();
        approveRadio.setToggleGroup(decisionGroup);
        denyRadio.setToggleGroup(decisionGroup);
    }

    private void loadRefundRequests() {
        refundRequests = FXCollections.observableArrayList(
                new RefundRequest("REF-001", "Tech Solutions Inc", "E-commerce Platform", 5000.0f, LocalDate.of(2024, 3, 10), "Pending", "Project requirements changed significantly"),
                new RefundRequest("REF-002", "Digital Marketing Co", "Social Media Campaign", 2500.0f, LocalDate.of(2024, 3, 12), "Pending", "Campaign did not meet agreed targets"),
                new RefundRequest("REF-003", "StartUp Ventures", "Mobile App Development", 8000.0f, LocalDate.of(2024, 3, 15), "Pending", "Technical issues not resolved in time"),
                new RefundRequest("REF-004", "Global Systems", "Database Migration", 3500.0f, LocalDate.of(2024, 3, 18), "Pending", "Data loss during migration process"),
                new RefundRequest("REF-005", "Innovation Labs", "AI Integration", 12000.0f, LocalDate.of(2024, 3, 20), "Pending", "AI model performance below expectations"),
                new RefundRequest("REF-006", "Creative Agency", "Website Redesign", 4000.0f, LocalDate.of(2024, 3, 22), "Approved", "Client requested design changes"),
                new RefundRequest("REF-007", "E-commerce Plus", "Payment Gateway", 6000.0f, LocalDate.of(2024, 3, 25), "Denied", "Service delivered as per agreement")
        );
        refundRequestsTable.setItems(refundRequests);
    }

    private void setupTableSelectionListener() {
        refundRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadRefundDetails(newSelection);
                updateStatusBtn.setDisable(false);
                setFieldsEditable(true);
            } else {
                clearSelection();
            }
        });
    }

    private void loadRefundDetails(RefundRequest request) {
        selectedRequestIdField.setText(request.getRequestId());
        selectedClientNameField.setText(request.getClientName());
        selectedProjectField.setText(request.getProjectName());
        selectedRefundAmountField.setText(String.format("%.2f", request.getRefundAmount()));
        selectedRequestDateField.setText(request.getRequestDate().toString());
        refundReasonArea.setText(request.getReason());

        if (request.getStatus().equals("Approved")) {
            approveRadio.setSelected(true);
        } else if (request.getStatus().equals("Denied")) {
            denyRadio.setSelected(true);
        } else {
            decisionGroup.selectToggle(null);
        }

        decisionReasonField.clear();
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onUpdateStatus(ActionEvent event) {
        RefundRequest selectedRequest = refundRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            showAlert("Please select a refund request", Alert.AlertType.WARNING);
            return;
        }

        if (selectedRequest.getStatus().equals("Approved") || selectedRequest.getStatus().equals("Denied")) {
            showAlert("This refund request has already been processed", Alert.AlertType.WARNING);
            return;
        }

        if (decisionGroup.getSelectedToggle() == null) {
            showAlert("Please select a decision (Approve or Deny)", Alert.AlertType.WARNING);
            return;
        }

        if (decisionReasonField.getText().trim().isEmpty()) {
            showAlert("Please enter a reason for your decision", Alert.AlertType.WARNING);
            return;
        }

        String decision = approveRadio.isSelected() ? "Approved" : "Denied";
        String reason = decisionReasonField.getText().trim();

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Decision");
        confirmAlert.setHeaderText("Refund Request Decision");
        confirmAlert.setContentText(String.format(
                "Request ID: %s\nClient: %s\nAmount: $%.2f\nDecision: %s\nReason: %s\n\nConfirm this decision?",
                selectedRequest.getRequestId(), selectedRequest.getClientName(),
                selectedRequest.getRefundAmount(), decision, reason));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedRequest.setStatus(decision);
            refundRequestsTable.refresh();

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Status Updated");
            successAlert.setHeaderText("Success");
            successAlert.setContentText(String.format("Refund request %s has been %s.",
                    selectedRequest.getRequestId(), decision.toLowerCase()));
            successAlert.showAndWait();

            clearSelection();
        }
    }

    private void clearSelection() {
        selectedRequestIdField.clear();
        selectedClientNameField.clear();
        selectedProjectField.clear();
        selectedRefundAmountField.clear();
        selectedRequestDateField.clear();
        refundReasonArea.clear();
        decisionReasonField.clear();
        decisionGroup.selectToggle(null);
        updateStatusBtn.setDisable(true);
        setFieldsEditable(false);
    }

    private void setFieldsEditable(boolean editable) {
        selectedRequestIdField.setEditable(false);
        selectedClientNameField.setEditable(false);
        selectedProjectField.setEditable(false);
        selectedRefundAmountField.setEditable(false);
        selectedRequestDateField.setEditable(false);
        refundReasonArea.setEditable(false);
        decisionReasonField.setEditable(editable);
        approveRadio.setDisable(!editable);
        denyRadio.setDisable(!editable);
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