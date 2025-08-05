package com.example.oop_project_group19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewerDashboardController {

    private Scene scene;

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile ));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheckMilestone(ActionEvent event) {
        switchScene(event, "Adnan/RcheckMilestone.fxml");
    }

    @FXML
    private void handleCreateReviewNote(ActionEvent event) {
        switchScene(event, "Adnan/RcreateReviewNote.fxml");
    }

    @FXML
    private void handleGenerateWeeklySummary(ActionEvent event) {
        switchScene(event, "Adnan/RgenerateWeeklySummary.fxml");
    }

    @FXML
    private void handleRateCompleteProject(ActionEvent event) {
        switchScene(event, "Adnan/RrateCompleteProject.fxml");
    }

    @FXML
    private void handleReviewFreelancer(ActionEvent event) {
        switchScene(event, "Adnan/RreviewFreelancer.fxml");
    }

    @FXML
    private void handleReviewProject(ActionEvent event) {
        switchScene(event, "Adnan/RreviewProject.fxml");
    }

    @FXML
    private void handleSendWarning(ActionEvent event) {
        switchScene(event, "Adnan/RsendWarning.fxml");
    }

    @FXML
    private void handleViewReviewHistory(ActionEvent event) {
        switchScene(event, "Adnan/RviewReviewHistory.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        switchScene(event, "loginScene.fxml");
    }
}
