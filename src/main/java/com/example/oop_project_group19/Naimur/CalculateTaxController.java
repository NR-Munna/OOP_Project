package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculateTaxController {

    @FXML
    private Button backBtn;

    @FXML
    private Button calculateTaxBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField incomeField;

    @FXML
    private TextField revenueField;

    @FXML
    private TextField taxPercentageField;

    @FXML
    private TextField taxableAmountField;

    @FXML
    private TextField totalTaxField;

    private Scene scene;

    @FXML
    public void initialize() {
        taxableAmountField.setEditable(false);
        totalTaxField.setEditable(false);
        clearFields();
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml");
    }

    @FXML
    void onCalculateTax(ActionEvent event) {
        if (validateInput()) {
            try {
                float revenue = Float.parseFloat(revenueField.getText().trim());
                float income = Float.parseFloat(incomeField.getText().trim());
                float taxPercentage = Float.parseFloat(taxPercentageField.getText().trim());

                if (taxPercentage < 0 || taxPercentage > 100) {
                    showAlert("Tax percentage must be between 0 and 100", Alert.AlertType.ERROR);
                    return;
                }

                float taxableAmount = revenue + income;
                float totalTax = (taxableAmount * taxPercentage) / 100;

                taxableAmountField.setText(String.format("%.2f", taxableAmount));
                totalTaxField.setText(String.format("%.2f", totalTax));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tax Calculation Complete");
                alert.setHeaderText("Tax Calculation Result");
                alert.setContentText(String.format(
                        "Revenue: $%.2f\nIncome: $%.2f\nTaxable Amount: $%.2f\nTax Rate: %.2f%%\nTotal Tax: $%.2f",
                        revenue, income, taxableAmount, taxPercentage, totalTax));
                alert.showAndWait();

            } catch (NumberFormatException e) {
                showAlert("Please enter valid numeric values", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onClear(ActionEvent event) {
        clearFields();
    }

    private boolean validateInput() {
        if (revenueField.getText().trim().isEmpty()) {
            showAlert("Revenue field is required", Alert.AlertType.ERROR);
            revenueField.requestFocus();
            return false;
        }

        if (incomeField.getText().trim().isEmpty()) {
            showAlert("Income field is required", Alert.AlertType.ERROR);
            incomeField.requestFocus();
            return false;
        }

        if (taxPercentageField.getText().trim().isEmpty()) {
            showAlert("Tax percentage field is required", Alert.AlertType.ERROR);
            taxPercentageField.requestFocus();
            return false;
        }

        try {
            float revenue = Float.parseFloat(revenueField.getText().trim());
            float income = Float.parseFloat(incomeField.getText().trim());
            float taxPercentage = Float.parseFloat(taxPercentageField.getText().trim());

            if (revenue < 0) {
                showAlert("Revenue cannot be negative", Alert.AlertType.ERROR);
                return false;
            }

            if (income < 0) {
                showAlert("Income cannot be negative", Alert.AlertType.ERROR);
                return false;
            }

            if (taxPercentage < 0) {
                showAlert("Tax percentage cannot be negative", Alert.AlertType.ERROR);
                return false;
            }

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void clearFields() {
        revenueField.clear();
        incomeField.clear();
        taxPercentageField.clear();
        taxableAmountField.clear();
        totalTaxField.clear();
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