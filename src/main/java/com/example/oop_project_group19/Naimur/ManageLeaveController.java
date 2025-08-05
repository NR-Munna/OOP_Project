package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageLeaveController {

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> daysColumn;

    @FXML
    private TableColumn<?, ?> employeeColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableView<?> leaveRequestsTable;

    @FXML
    private TableColumn<?, ?> leaveTypeColumn;

    @FXML
    private TableColumn<?, ?> reasonColumn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Label selectedEmployeeLabel;

    @FXML
    private Label selectedLeaveTypeLabel;

    @FXML
    private Label selectedReasonLabel;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    void onApproveRequest(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @Deprecated
    void onRefresh(ActionEvent event) {

    }

    @FXML
    void onRejectRequest(ActionEvent event) {

    }

}
