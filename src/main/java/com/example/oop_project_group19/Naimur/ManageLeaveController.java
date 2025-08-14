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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageLeaveController implements Initializable {

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<LeaveRequest, Integer> daysColumn;

    @FXML
    private TableColumn<LeaveRequest, String> employeeColumn;

    @FXML
    private TableColumn<LeaveRequest, LocalDate> endDateColumn;

    @FXML
    private TableView<LeaveRequest> leaveRequestsTable;

    @FXML
    private TableColumn<LeaveRequest, String> leaveTypeColumn;

    @FXML
    private TableColumn<LeaveRequest, String> reasonColumn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Label selectedEmployeeLabel;

    @FXML
    private Label selectedLeaveTypeLabel;

    @FXML
    private Label selectedReasonLabel;

    @FXML
    private TableColumn<LeaveRequest, LocalDate> startDateColumn;

    private Scene scene;
    private ObservableList<LeaveRequest> leaveRequests;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        loadLeaveRequests();
        setupTableSelectionListener();
    }

    private void initializeTable() {
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
        leaveTypeColumn.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }

    private void loadLeaveRequests() {
        leaveRequests = FXCollections.observableArrayList(
                new LeaveRequest("John Doe", "Sick Leave", LocalDate.of(2024, 3, 10), LocalDate.of(2024, 3, 12), 3, "Flu symptoms"),
                new LeaveRequest("Jane Smith", "Annual Leave", LocalDate.of(2024, 3, 15), LocalDate.of(2024, 3, 20), 5, "Family vacation"),
                new LeaveRequest("Mike Johnson", "Personal Leave", LocalDate.of(2024, 3, 18), LocalDate.of(2024, 3, 19), 2, "Personal matters"),
                new LeaveRequest("Sarah Wilson", "Sick Leave", LocalDate.of(2024, 3, 22), LocalDate.of(2024, 3, 24), 3, "Medical appointment"),
                new LeaveRequest("David Brown", "Annual Leave", LocalDate.of(2024, 3, 25), LocalDate.of(2024, 4, 1), 7, "Holiday trip")
        );
        leaveRequestsTable.setItems(leaveRequests);
    }

    private void setupTableSelectionListener() {
        leaveRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEmployeeLabel.setText("Employee: " + newSelection.getEmployee());
                selectedLeaveTypeLabel.setText("Leave Type: " + newSelection.getLeaveType());
                selectedReasonLabel.setText("Reason: " + newSelection.getReason());
            } else {
                selectedEmployeeLabel.setText("Employee: None selected");
                selectedLeaveTypeLabel.setText("Leave Type: None selected");
                selectedReasonLabel.setText("Reason: None selected");
            }
        });
    }

    @FXML
    void onApproveRequest(ActionEvent event) {
        LeaveRequest selectedRequest = leaveRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            showAlert("Please select a leave request to approve", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leave Request Approved");
        alert.setHeaderText("Success");
        alert.setContentText("Leave request for " + selectedRequest.getEmployee() + " has been approved.");
        alert.showAndWait();

        leaveRequests.remove(selectedRequest);
        clearSelection();
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onRejectRequest(ActionEvent event) {
        LeaveRequest selectedRequest = leaveRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            showAlert("Please select a leave request to reject", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leave Request Rejected");
        alert.setHeaderText("Success");
        alert.setContentText("Leave request for " + selectedRequest.getEmployee() + " has been rejected.");
        alert.showAndWait();

        leaveRequests.remove(selectedRequest);
        clearSelection();
    }

    private void clearSelection() {
        selectedEmployeeLabel.setText("Employee: None selected");
        selectedLeaveTypeLabel.setText("Leave Type: None selected");
        selectedReasonLabel.setText("Reason: None selected");
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