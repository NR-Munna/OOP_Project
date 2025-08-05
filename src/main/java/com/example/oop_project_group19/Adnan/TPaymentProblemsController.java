package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TPaymentProblemsController {

    @FXML
    private RadioButton allIssuesRadio;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private Button checkTransactionBtn;

    @FXML
    private Button filterBtn;

    @FXML
    private RadioButton inProgressIssuesRadio;

    @FXML
    private ComboBox<?> investigationStatusCombo;

    @FXML
    private TextArea issueDescriptionArea;

    @FXML
    private TableColumn<?, ?> issueIdColumn;

    @FXML
    private ToggleGroup issueStatusGroup;

    @FXML
    private TableColumn<?, ?> issueTypeColumn;

    @FXML
    private TableView<?> paymentIssuesTable;

    @FXML
    private TextField paymentMethodField;

    @FXML
    private RadioButton pendingIssuesRadio;

    @FXML
    private Button refundBtn;

    @FXML
    private TextField resolutionNotesField;

    @FXML
    private Button resolvedBtn;

    @FXML
    private RadioButton resolvedIssuesRadio;

    @FXML
    private TextField selectedAmountField;

    @FXML
    private TextField selectedTransactionIdField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> timestampColumn;

    @FXML
    private TableColumn<?, ?> transactionIdColumn;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    void handleCheckTransaction(ActionEvent event) {

    }

    @FXML
    void handleFilter(ActionEvent event) {

    }

    @FXML
    void handleMarkResolved(ActionEvent event) {

    }

    @FXML
    void handleRefund(ActionEvent event) {

    }

}
