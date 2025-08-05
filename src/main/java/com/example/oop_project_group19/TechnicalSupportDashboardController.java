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

public class TechnicalSupportDashboardController {

    @FXML
    private Button viewTicketQueueBtn;
    @FXML
    private Button updateTicketStatusBtn;
    @FXML
    private Button passwordResetBtn;
    @FXML
    private Button reviewsComplaintsBtn;
    @FXML
    private Button monitorActivityBtn;
    @FXML
    private Button paymentProblemsBtn;
    @FXML
    private Button bugReportsBtn;
    @FXML
    private Button chatSupportBtn;
    @FXML
    private Button logoutBtn;

    private Scene scene;

    @FXML
    private void handleViewTicketQueue(ActionEvent event) {
        switchScene(event, "Adnan/ViewTicketQueue.fxml");
    }

    @FXML
    private void handleUpdateTicketStatus(ActionEvent event) {
        switchScene(event, "Adnan/UpdateTicketStatus.fxml");
    }

    @FXML
    private void handlePasswordReset(ActionEvent event) {
        switchScene(event, "Adnan/PasswordResetRequests.fxml");
    }

    @FXML
    private void handleReviewsComplaints(ActionEvent event) {
        switchScene(event, "Adnan/ReviewsComplaints.fxml");
    }

    @FXML
    private void handleMonitorActivity(ActionEvent event) {
        switchScene(event, "Adnan/MonitorUserActivity.fxml");
    }

    @FXML
    private void handlePaymentProblems(ActionEvent event) {
        switchScene(event, "Adnan/PaymentProblems.fxml");
    }

    @FXML
    private void handleBugReports(ActionEvent event) {
        switchScene(event, "Adnan/BugReports.fxml");
    }

    @FXML
    private void handleChatSupport(ActionEvent event) {
        switchScene(event, "Adnan/ChatSupport.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
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