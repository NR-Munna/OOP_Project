package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HandleRefundsController {

    @FXML
    private RadioButton approveRadio;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> clientNameColumn;

    @FXML
    private TextField decisionReasonField;

    @FXML
    private RadioButton denyRadio;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private TableColumn<?, ?> refundAmountColumn;

    @FXML
    private TextArea refundReasonArea;

    @FXML
    private TableView<?> refundRequestsTable;

    @FXML
    private TableColumn<?, ?> requestDateColumn;

    @FXML
    private TableColumn<?, ?> requestIdColumn;

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
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Button updateStatusBtn;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onUpdateStatus(ActionEvent event) {

    }

}
