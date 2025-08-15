package com.example.oop_project_group19.Adnan;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TPaymentProblemsController {

    @FXML private RadioButton allIssuesRadio;
    @FXML private TableColumn<PaymentIssue, Float> amountColumn;
    @FXML private Button backBtn;
    @FXML private Button checkTransactionBtn;
    @FXML private Button filterBtn;
    @FXML private RadioButton inProgressIssuesRadio;
    @FXML private ComboBox<String> investigationStatusCombo;
    @FXML private TextArea issueDescriptionArea;
    @FXML private TableColumn<PaymentIssue, String> issueIdColumn;
    @FXML private ToggleGroup issueStatusGroup;
    @FXML private TableColumn<PaymentIssue, String> issueTypeColumn;
    @FXML private TableView<PaymentIssue> paymentIssuesTable;
    @FXML private TextField paymentMethodField;
    @FXML private RadioButton pendingIssuesRadio;
    @FXML private Button refundBtn;
    @FXML private TextField resolutionNotesField;
    @FXML private Button resolvedBtn;
    @FXML private RadioButton resolvedIssuesRadio;
    @FXML private TextField selectedAmountField;
    @FXML private TextField selectedTransactionIdField;
    @FXML private TableColumn<PaymentIssue, String> statusColumn;
    @FXML private TableColumn<PaymentIssue, LocalDateTime> timestampColumn;
    @FXML private TableColumn<PaymentIssue, String> transactionIdColumn;
    @FXML private TableColumn<PaymentIssue, String> userIdColumn;
    @FXML private TableColumn<PaymentIssue, String> usernameColumn;

    private Scene scene;
    private ObservableList<PaymentIssue> issuesList = FXCollections.observableArrayList();
    private ObservableList<PaymentIssue> allIssues = FXCollections.observableArrayList();

    public void initialize() {
        issueIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIssueId()));
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        transactionIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTransactionId()));
        amountColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getAmount()).asObject());
        issueTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIssueType()));
        timestampColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTimestamp()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        investigationStatusCombo.setItems(FXCollections.observableArrayList("Under Investigation", "Pending Review", "Resolved", "Refunded"));

        allIssuesRadio.setSelected(true);

        loadSampleData();
        allIssues.addAll(issuesList);
        paymentIssuesTable.setItems(issuesList);

        paymentIssuesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTransactionIdField.setText(newSelection.getTransactionId());
                selectedAmountField.setText(String.valueOf(newSelection.getAmount()));
                issueDescriptionArea.setText(newSelection.getDescription());
                paymentMethodField.setText("Credit Card");
                investigationStatusCombo.setValue(newSelection.getStatus());
            }
        });
    }

    private void loadSampleData() {
        issuesList.add(new PaymentIssue("PI001", "U001", "Christian Williams", "TXN001", 150.0f, "Payment Failed", "Credit card declined"));
        issuesList.add(new PaymentIssue("PI002", "U002", "Jane Smith", "TXN002", 75.5f, "Double Charge", "Charged twice for same transaction"));
        issuesList.add(new PaymentIssue("PI003", "U003", "Bob Johnson", "TXN003", 200.0f, "Refund Request", "Requesting refund for cancelled service"));
        issuesList.add(new PaymentIssue("PI004", "U004", "Alice Brown", "TXN004", 89.99f, "Payment Error", "Transaction stuck in processing"));
    }

    @FXML
    void handleCheckTransaction(ActionEvent event) {
        PaymentIssue selected = paymentIssuesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String transactionInfo = "Transaction Details:\n" +
                    "Transaction ID: " + selected.getTransactionId() + "\n" +
                    "Amount: $" + selected.getAmount() + "\n" +
                    "Status: Processing\n" +
                    "Payment Method: Credit Card\n" +
                    "Timestamp: " + selected.getTimestamp();

            issueDescriptionArea.setText(transactionInfo);
            System.out.println("Transaction checked: " + selected.getTransactionId());
        }
    }

    @FXML
    void handleFilter(ActionEvent event) {
        ObservableList<PaymentIssue> filteredList = FXCollections.observableArrayList();

        if (allIssuesRadio.isSelected()) {
            filteredList.addAll(allIssues);
        } else if (pendingIssuesRadio.isSelected()) {
            for (PaymentIssue issue : allIssues) {
                if (issue.getStatus().equals("Pending")) {
                    filteredList.add(issue);
                }
            }
        } else if (inProgressIssuesRadio.isSelected()) {
            for (PaymentIssue issue : allIssues) {
                if (issue.getStatus().equals("Under Investigation")) {
                    filteredList.add(issue);
                }
            }
        } else if (resolvedIssuesRadio.isSelected()) {
            for (PaymentIssue issue : allIssues) {
                if (issue.getStatus().equals("Resolved")) {
                    filteredList.add(issue);
                }
            }
        }

        paymentIssuesTable.setItems(filteredList);
    }

    @FXML
    void handleMarkResolved(ActionEvent event) {
        PaymentIssue selected = paymentIssuesTable.getSelectionModel().getSelectedItem();
        String notes = resolutionNotesField.getText();

        if (selected != null) {
            selected.setStatus("Resolved");
            paymentIssuesTable.refresh();

            if (!notes.isEmpty()) {
                issueDescriptionArea.setText("RESOLVED: " + notes + "\n\n" + selected.getDescription());
                resolutionNotesField.clear();
            }

            System.out.println("Issue resolved: " + selected.getIssueId());
        }
    }

    @FXML
    void handleRefund(ActionEvent event) {
        PaymentIssue selected = paymentIssuesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Refunded");
            paymentIssuesTable.refresh();
            issueDescriptionArea.setText("REFUND PROCESSED: $" + selected.getAmount() + " refunded to user\n\n" + selected.getDescription());
            System.out.println("Refund processed for: " + selected.getIssueId() + " Amount: $" + selected.getAmount());
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
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