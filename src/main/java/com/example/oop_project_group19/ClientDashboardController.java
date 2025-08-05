package com.example.oop_project_group19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientDashboardController {

    @FXML
    private Button approvePaymentButton;

    @FXML
    private Button chatButton;

    @FXML
    private Button hireFreelancerButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button postJobButton;

    @FXML
    private Button provideFeedbackButton;

    @FXML
    private Button reviewWorkButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button viewHistoryButton;

    @FXML
    private Button viewProposalsButton;

    private Scene scene;

    @FXML
    void handleApprovePayment(ActionEvent event) {
        switchScene(event, "Shawon/ApprovePaymentScene.fxml");
    }

    @FXML
    void handleChat(ActionEvent event) {
        switchScene(event, "Shawon/ChatScene.fxml");
    }

    @FXML
    void handleHireFreelancer(ActionEvent event) {
        switchScene(event, "Shawon/HireFreelancerScene.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        switchScene(event, "loginScene.fxml");
    }

    @FXML
    void handlePostJob(ActionEvent event) {
        switchScene(event, "Shawon/PostJobScene.fxml");
    }

    @FXML
    void handleProvideFeedback(ActionEvent event) {
        switchScene(event, "Shawon/ProvideFeedbackScene.fxml");
    }

    @FXML
    void handleReviewWork(ActionEvent event) {
        switchScene(event, "Shawon/ReviewWorkScene.fxml");
    }

    @FXML
    void handleViewHistory(ActionEvent event) {
        switchScene(event, "Shawon/ViewHistoryScene.fxml");
    }

    @FXML
    void handleViewProposals(ActionEvent event) {
        switchScene(event, "Shawon/ViewProposalsScene.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {

            if (getClass().getResource(fxmlFile) == null) {
                System.err.println("FXML file not found: " + fxmlFile);
                if (statusLabel != null) {
                    statusLabel.setText("Scene not available: " + fxmlFile);
                }
                return;
            }

            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + fxmlFile);
            if (statusLabel != null) {
                statusLabel.setText("Error loading scene: " + fxmlFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unexpected error: " + e.getMessage());
            if (statusLabel != null) {
                statusLabel.setText("Unexpected error occurred");
            }
        }
    }
}