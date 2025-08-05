package com.example.oop_project_group19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceOfficerDashboardController {

    @FXML
    private Button processInvoicesBtn;
    @FXML
    private Button calculateTaxBtn;
    @FXML
    private Button generatePayStubsBtn;
    @FXML
    private Button trackExpensesBtn;
    @FXML
    private Button monitorCashFlowBtn;
    @FXML
    private Button handleRefundsBtn;
    @FXML
    private Button createBudgetBtn;
    @FXML
    private Button loanManagementBtn;
    @FXML
    private Button logoutBtn;

    private Scene scene;

    @FXML
    private void onProcessInvoices(ActionEvent event) {
        switchScene(event, "Naimur/processInvoices.fxml");
    }

    @FXML
    private void onCalculateTax(ActionEvent event) {
        switchScene(event, "Naimur/calculateTax.fxml");
    }

    @FXML
    private void onGeneratePayStubs(ActionEvent event) {
        switchScene(event, "Naimur/generatePayStubs.fxml");
    }

    @FXML
    private void onTrackExpenses(ActionEvent event) {
        switchScene(event, "Naimur/trackExpenses.fxml");
    }

    @FXML
    private void onMonitorCashFlow(ActionEvent event) {
        switchScene(event, "Naimur/monitorCashFlow.fxml");
    }

    @FXML
    private void onHandleRefunds(ActionEvent event) {
        switchScene(event, "Naimur/handleRefunds.fxml");
    }

    @FXML
    private void onCreateBudget(ActionEvent event) {
        switchScene(event, "Naimur/createBudget.fxml");
    }

    @FXML
    private void onLoanManagement(ActionEvent event) {
        switchScene(event, "Naimur/loanManagement.fxml");
    }

    @FXML
    private void onLogout(ActionEvent event) {

        switchScene(event, "loginScene.fxml");
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