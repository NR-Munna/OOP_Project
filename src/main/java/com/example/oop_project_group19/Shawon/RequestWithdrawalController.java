package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestWithdrawalController implements Initializable {

    @FXML private TextField accountHolderField;
    @FXML private TextField accountNumberField;
    @FXML private TextField availableBalanceField;
    @FXML private Button backButton;
    @FXML private TextField bankNameField;
    @FXML private Button maxButton;
    @FXML private TextField minimumWithdrawalField;
    @FXML private TextField netAmountField;
    @FXML private TextArea notesArea;
    @FXML private ComboBox<String> paymentMethodCombo;
    @FXML private CheckBox processingFeeCheckBox;
    @FXML private Button requestWithdrawalButton;
    @FXML private TextField routingNumberField;
    @FXML private Button saveAccountButton;
    @FXML private Label statusLabel;
    @FXML private TextField swiftCodeField;
    @FXML private CheckBox verifyDetailsCheckBox;
    @FXML private TextField withdrawalAmountField;
    @FXML private TextField withdrawalFeeField;

    private Scene scene;
    private static final float MINIMUM_WITHDRAWAL = 50.0f;
    private static final float WITHDRAWAL_FEE = 5.0f;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentMethodCombo.setItems(FXCollections.observableArrayList(
                "Bank Transfer", "PayPal", "Stripe", "Wire Transfer"
        ));

        availableBalanceField.setEditable(false);
        minimumWithdrawalField.setEditable(false);
        withdrawalFeeField.setEditable(false);
        netAmountField.setEditable(false);

        minimumWithdrawalField.setText(String.format("$%.2f", MINIMUM_WITHDRAWAL));
        withdrawalFeeField.setText(String.format("$%.2f", WITHDRAWAL_FEE));

        withdrawalAmountField.textProperty().addListener((obs, oldText, newText) -> {
            calculateNetAmount();
        });

        processingFeeCheckBox.setSelected(true);
    }

    public void setAvailableBalance(String balance) {
        availableBalanceField.setText(balance);
    }

    private void calculateNetAmount() {
        try {
            String amountText = withdrawalAmountField.getText().replace("$", "");
            if (!amountText.isEmpty()) {
                float amount = Float.parseFloat(amountText);
                float fee = processingFeeCheckBox.isSelected() ? WITHDRAWAL_FEE : 0;
                float netAmount = amount - fee;
                netAmountField.setText(String.format("$%.2f", netAmount));
            }
        } catch (NumberFormatException e) {
            netAmountField.setText("$0.00");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleMaxAmount(ActionEvent event) {
        String balanceText = availableBalanceField.getText().replace("$", "");
        withdrawalAmountField.setText(balanceText);
        calculateNetAmount();
    }

    @FXML
    void handlePaymentMethodChange(ActionEvent event) {
        String selectedMethod = paymentMethodCombo.getValue();
        if (selectedMethod != null) {
            switch (selectedMethod) {
                case "Bank Transfer":
                    bankNameField.setDisable(false);
                    accountNumberField.setDisable(false);
                    routingNumberField.setDisable(false);
                    swiftCodeField.setDisable(true);
                    break;
                case "Wire Transfer":
                    bankNameField.setDisable(false);
                    accountNumberField.setDisable(false);
                    routingNumberField.setDisable(false);
                    swiftCodeField.setDisable(false);
                    break;
                case "PayPal":
                case "Stripe":
                    bankNameField.setDisable(true);
                    accountNumberField.setDisable(true);
                    routingNumberField.setDisable(true);
                    swiftCodeField.setDisable(true);
                    break;
            }
        }
    }

    @FXML
    void handleRequestWithdrawal(ActionEvent event) {
        if (validateWithdrawal()) {
            statusLabel.setText("Withdrawal request submitted successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Withdrawal Requested");
            alert.setContentText("Your withdrawal request has been submitted. " +
                    "Processing time: 3-5 business days.\n" +
                    "You will receive a confirmation email shortly.");
            alert.showAndWait();

            switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
        }
    }

    private boolean validateWithdrawal() {
        try {
            String amountText = withdrawalAmountField.getText().replace("$", "");
            float amount = Float.parseFloat(amountText);

            if (amount < MINIMUM_WITHDRAWAL) {
                statusLabel.setText("Amount must be at least $" + MINIMUM_WITHDRAWAL);
                statusLabel.setStyle("-fx-text-fill: red;");
                return false;
            }

            String balanceText = availableBalanceField.getText().replace("$", "");
            float availableBalance = Float.parseFloat(balanceText);

            if (amount > availableBalance) {
                statusLabel.setText("Amount exceeds available balance");
                statusLabel.setStyle("-fx-text-fill: red;");
                return false;
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid withdrawal amount");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (paymentMethodCombo.getValue() == null) {
            statusLabel.setText("Please select a payment method");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (!verifyDetailsCheckBox.isSelected()) {
            statusLabel.setText("Please verify your account details");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        String method = paymentMethodCombo.getValue();
        if ("Bank Transfer".equals(method) || "Wire Transfer".equals(method)) {
            if (accountHolderField.getText().trim().isEmpty() ||
                    accountNumberField.getText().trim().isEmpty() ||
                    bankNameField.getText().trim().isEmpty()) {
                statusLabel.setText("Please fill in all required bank details");
                statusLabel.setStyle("-fx-text-fill: red;");
                return false;
            }
        }

        return true;
    }

    @FXML
    void handleSaveAccount(ActionEvent event) {
        if (paymentMethodCombo.getValue() != null) {
            statusLabel.setText("Account details saved");
            statusLabel.setStyle("-fx-text-fill: blue;");
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
