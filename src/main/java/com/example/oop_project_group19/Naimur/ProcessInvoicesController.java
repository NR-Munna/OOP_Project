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

public class ProcessInvoicesController {

    @FXML
    private TableColumn<Invoice, Float> amountColumn;

    @FXML
    private Button backBtn;

    @FXML
    private Button clearPaymentBtn;

    @FXML
    private TableColumn<Invoice, String> clientNameColumn;

    @FXML
    private TableColumn<Invoice, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceIdColumn;

    @FXML
    private TableView<Invoice> invoiceTable;

    @FXML
    private TextField selectedAmountField;

    @FXML
    private TextField selectedClientField;

    @FXML
    private TextField selectedDueDateField;

    @FXML
    private TextField selectedInvoiceIdField;

    @FXML
    private TableColumn<Invoice, String> statusColumn;

    private Scene scene;
    private ObservableList<Invoice> invoices;

    @FXML
    public void initialize() {
        initializeTable();
        loadInvoices();
        setupTableSelectionListener();
        clearSelection();
    }

    private void initializeTable() {
        invoiceIdColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadInvoices() {
        invoices = FXCollections.observableArrayList(
                new Invoice("INV-001", "Tech Solutions Inc", 15000.0f, LocalDate.of(2024, 3, 15), "Pending"),
                new Invoice("INV-002", "Digital Marketing Co", 8500.0f, LocalDate.of(2024, 3, 20), "Pending"),
                new Invoice("INV-003", "StartUp Ventures", 12000.0f, LocalDate.of(2024, 3, 25), "Pending"),
                new Invoice("INV-004", "Global Systems", 22000.0f, LocalDate.of(2024, 3, 30), "Pending"),
                new Invoice("INV-005", "Innovation Labs", 9800.0f, LocalDate.of(2024, 4, 5), "Pending"),
                new Invoice("INV-006", "Creative Agency", 6500.0f, LocalDate.of(2024, 4, 10), "Pending"),
                new Invoice("INV-007", "E-commerce Plus", 18000.0f, LocalDate.of(2024, 4, 15), "Pending")
        );
        invoiceTable.setItems(invoices);
    }

    private void setupTableSelectionListener() {
        invoiceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedInvoiceIdField.setText(newSelection.getInvoiceId());
                selectedClientField.setText(newSelection.getClientName());
                selectedAmountField.setText(String.format("%.2f", newSelection.getAmount()));
                selectedDueDateField.setText(newSelection.getDueDate().toString());
                clearPaymentBtn.setDisable(false);
            } else {
                clearSelection();
            }
        });
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onClearPayment(ActionEvent event) {
        Invoice selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            showAlert("Please select an invoice to process", Alert.AlertType.WARNING);
            return;
        }

        if (selectedInvoice.getStatus().equals("Paid")) {
            showAlert("This invoice has already been paid", Alert.AlertType.INFORMATION);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Payment");
        confirmAlert.setHeaderText("Process Payment");
        confirmAlert.setContentText(String.format("Are you sure you want to clear payment for invoice %s?\nAmount: $%.2f\nClient: %s",
                selectedInvoice.getInvoiceId(), selectedInvoice.getAmount(), selectedInvoice.getClientName()));

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            selectedInvoice.setStatus("Paid");
            invoiceTable.refresh();

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Payment Processed");
            successAlert.setHeaderText("Success");
            successAlert.setContentText(String.format("Payment of $%.2f for invoice %s has been successfully processed.",
                    selectedInvoice.getAmount(), selectedInvoice.getInvoiceId()));
            successAlert.showAndWait();

            clearSelection();
        }
    }

    private void clearSelection() {
        selectedInvoiceIdField.clear();
        selectedClientField.clear();
        selectedAmountField.clear();
        selectedDueDateField.clear();
        clearPaymentBtn.setDisable(true);
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