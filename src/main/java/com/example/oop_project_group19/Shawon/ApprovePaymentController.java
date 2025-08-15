package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class ApprovePaymentController {
    @javafx.fxml.FXML
    private TableColumn<String, String> actionColumn;
    @javafx.fxml.FXML
    private TextField projectTitleField;
    @javafx.fxml.FXML
    private TextField freelancerNameField;
    @javafx.fxml.FXML
    private TextField platformFeeField;
    @javafx.fxml.FXML
    private TableColumn<String, Double> amountColumn;
    @javafx.fxml.FXML
    private ComboBox<Project> projectComboBox;
    @javafx.fxml.FXML
    private TextField contractAmountField;
    @javafx.fxml.FXML
    private TableView<String> paymentBreakdownTable;
    @javafx.fxml.FXML
    private CheckBox confirmWorkCheckBox;
    @javafx.fxml.FXML
    private TextField bonusAmountField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TextField workStatusField;
    @javafx.fxml.FXML
    private ComboBox<String> paymentMethodCombo;
    @javafx.fxml.FXML
    private TableColumn<String, String> dueDateColumn;
    @javafx.fxml.FXML
    private Button refreshButton;
    @javafx.fxml.FXML
    private CheckBox bonusCheckBox;
    @javafx.fxml.FXML
    private TableColumn<String, String> statusColumn;
    @javafx.fxml.FXML
    private Button holdPaymentButton;
    @javafx.fxml.FXML
    private TextField totalAmountField;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Button approvePaymentButton;
    @javafx.fxml.FXML
    private TextField subtotalField;
    @javafx.fxml.FXML
    private TableColumn<String, String> descriptionColumn;

    private Scene scene;

    @javafx.fxml.FXML
    public void initialize() {
        projectComboBox.getItems().addAll(
                new Project("P1", "Website Development", "C1", "F1", "Shakib Khan", null, null, 1500.0, "Completed"),
                new Project("P2", "Mobile App", "C1", "F2", "Ananta Jalil", null, null, 2000.0, "Completed")
        );

        paymentMethodCombo.getItems().addAll(
                "PayPal", "Bank Transfer", "Credit Card", "Escrow Service"
        );

        platformFeeField.setText("5.0%");
        workStatusField.setEditable(false);
        projectTitleField.setEditable(false);
        freelancerNameField.setEditable(false);
        contractAmountField.setEditable(false);
        subtotalField.setEditable(false);
        totalAmountField.setEditable(false);

        bonusAmountField.setDisable(true);
        bonusCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            bonusAmountField.setDisable(!newVal);
            if (!newVal) {
                bonusAmountField.clear();
            }
            updateTotalAmount();
        });

        bonusAmountField.textProperty().addListener((obs, oldVal, newVal) -> updateTotalAmount());
    }

    @javafx.fxml.FXML
    public void handleBack(ActionEvent actionEvent) {
        switchScene(actionEvent, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @javafx.fxml.FXML
    public void handleApprovePayment(ActionEvent actionEvent) {
        if (projectComboBox.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        if (!confirmWorkCheckBox.isSelected()) {
            statusLabel.setText("Please confirm that work has been submitted");
            return;
        }

        if (paymentMethodCombo.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select payment method");
            return;
        }

        statusLabel.setText("Payment Successful! Invoice generated and payment processed.");

        confirmWorkCheckBox.setSelected(false);
        bonusCheckBox.setSelected(false);
        bonusAmountField.clear();
    }

    @javafx.fxml.FXML
    public void handleProjectSelection(ActionEvent actionEvent) {
        Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            projectTitleField.setText(selectedProject.getTitle());
            freelancerNameField.setText(selectedProject.getFreelancerName());
            contractAmountField.setText(String.format("%.2f", selectedProject.getBudget()));
            workStatusField.setText("Submitted - Pending Review");

            updateTotalAmount();
            statusLabel.setText("Project details loaded. Verifying work submission...");
        }
    }

    @javafx.fxml.FXML
    public void handleRefresh(ActionEvent actionEvent) {
        if (projectComboBox.getSelectionModel().getSelectedItem() != null) {
            handleProjectSelection(actionEvent);
            statusLabel.setText("Payment details refreshed");
        }
    }

    @javafx.fxml.FXML
    public void handleHoldPayment(ActionEvent actionEvent) {
        if (projectComboBox.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        statusLabel.setText("Payment held for review");
    }

    private void updateTotalAmount() {
        try {
            double contractAmount = Double.parseDouble(contractAmountField.getText());
            double platformFee = contractAmount * 0.05;
            double bonus = 0.0;

            if (bonusCheckBox.isSelected() && !bonusAmountField.getText().isEmpty()) {
                bonus = Double.parseDouble(bonusAmountField.getText());
            }

            double subtotal = contractAmount + platformFee + bonus;

            subtotalField.setText(String.format("%.2f", subtotal));
            totalAmountField.setText(String.format("%.2f", subtotal));
        } catch (NumberFormatException e) {

        }
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