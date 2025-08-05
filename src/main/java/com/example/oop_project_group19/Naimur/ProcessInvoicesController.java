package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProcessInvoicesController {

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private Button backBtn;

    @FXML
    private Button clearPaymentBtn;

    @FXML
    private TableColumn<?, ?> clientNameColumn;

    @FXML
    private TableColumn<?, ?> dueDateColumn;

    @FXML
    private TableColumn<?, ?> invoiceIdColumn;

    @FXML
    private TableView<?> invoiceTable;

    @FXML
    private TextField selectedAmountField;

    @FXML
    private TextField selectedClientField;

    @FXML
    private TextField selectedDueDateField;

    @FXML
    private TextField selectedInvoiceIdField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onClearPayment(ActionEvent event) {

    }

}
