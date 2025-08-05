package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LoanManagementController {

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> clientNameColumn;

    @FXML
    private TableColumn<?, ?> delayIdColumn;

    @FXML
    private TableColumn<?, ?> delayStatusColumn;

    @FXML
    private TableColumn<?, ?> delayedAmountColumn;

    @FXML
    private Button denyBtn;

    @FXML
    private TableColumn<?, ?> freelancerNameColumn;

    @FXML
    private TableColumn<?, ?> loanAmountColumn;

    @FXML
    private TableColumn<?, ?> loanRequestIdColumn;

    @FXML
    private TableView<?> loanRequestsTable;

    @FXML
    private TableColumn<?, ?> loanStatusColumn;

    @FXML
    private TableColumn<?, ?> originalDueDateColumn;

    @FXML
    private TableView<?> paymentDelaysTable;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private TableColumn<?, ?> requestDateColumn;

    @FXML
    private TextArea requestReasonArea;

    @FXML
    private TextField selectedAmountField;

    @FXML
    private TextField selectedApplicantField;

    @FXML
    private TextField selectedRequestIdField;

    @FXML
    private TableColumn<?, ?> urgencyColumn;

    @FXML
    void onApprove(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onDeny(ActionEvent event) {

    }

}
