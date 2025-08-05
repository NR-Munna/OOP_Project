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

public class AdminDashboardController {

    @FXML
    private Button exportUserDataButton;

    @FXML
    private Button generateReportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchUsersButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button systemStatisticsButton;

    @FXML
    private Button updateUserStatusButton;

    @FXML
    private Button viewAllProjectsButton;

    @FXML
    private Button viewAllUsersButton;

    @FXML
    private Button viewUserDetailsButton;

    private Scene scene;

    @FXML
    void handleExportUserData(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminExportUserData.fxml");
    }

    @FXML
    void handleGenerateReports(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminGenerateReport.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/loginScene.fxml");
    }

    @FXML
    void handleSearchUsers(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminSearchUsers.fxml");
    }

    @FXML
    void handleSystemStatistics(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminSystemStatistics.fxml");
    }

    @FXML
    void handleUpdateUserStatus(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminUpdateUserStatus.fxml");
    }

    @FXML
    void handleViewAllProjects(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminViewAllProjects.fxml");
    }

    @FXML
    void handleViewAllUsers(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminViewAllUsers.fxml");
    }

    @FXML
    void handleViewUserDetails(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/AdminViewUserDetails.fxml");
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