package com.example.oop_project_group19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class FreelancerDashboardController {

    @FXML
    private Button acceptOfferButton;

    @FXML
    private Button browseJobsButton;

    @FXML
    private Button communicateButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button requestWithdrawalButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitProposalButton;

    @FXML
    private Button trackEarningsButton;

    @FXML
    private Button uploadWorkButton;

    private Scene scene;

    @FXML
    void handleAcceptOffer(ActionEvent event) {
        switchScene(event, "Shawon/AcceptOfferScene.fxml");
    }

    @FXML
    void handleBrowseJobs(ActionEvent event) {
        switchScene(event, "Shawon/BrowseJobsScene.fxml");
    }

    @FXML
    void handleCommunicate(ActionEvent event) {
        switchScene(event, "Shawon/CommunicateClientScene.fxml");
    }

    @FXML
    void handleEditProfile(ActionEvent event) {
        switchScene(event, "Shawon/EditProfileScene.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        switchScene(event, "loginScene.fxml");
    }



    @FXML
    void handleRequestWithdrawal(ActionEvent event) {
        switchScene(event, "Shawon/RequestWithdrawalScene.fxml");
    }

    @FXML
    void handleSubmitProposal(ActionEvent event) {
        switchScene(event, "Shawon/SubmitProposalScene.fxml");
    }

    @FXML
    void handleTrackEarnings(ActionEvent event) {
        switchScene(event, "Shawon/TrackEarningsScene.fxml");
    }

    @FXML
    void handleUploadWork(ActionEvent event) {
        switchScene(event, "Shawon/UploadWorkScene.fxml");
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
