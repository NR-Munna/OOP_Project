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

public class ProjectManagerDashboardController {

    @FXML
    private Button archiveProjectsButton;

    @FXML
    private Button assignFreelancersButton;

    @FXML
    private Button createChecklistButton;

    @FXML
    private Button createScheduleButton;

    @FXML
    private Button editProjectButton;

    @FXML
    private Button editProjectDetailsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button manageDeadlinesButton;

    @FXML
    private Button monitorBudgetButton;

    @FXML
    private Label statusLabel;

    private Scene scene;

    @FXML
    void handleArchiveProjects(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMArchiveProjects.fxml");
    }

    @FXML
    void handleAssignFreelancers(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMAssignFreelancers.fxml");
    }

    @FXML
    void handleCreateChecklist(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMCreateChecklist.fxml");
    }

    @FXML
    void handleCreateSchedule(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMCreateSchedule.fxml");
    }

    @FXML
    void handleEditProject(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMEditProjectUpdates.fxml");
    }

    @FXML
    void handleEditProjectDetails(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMEditProjectDetails.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/loginScene.fxml");
    }

    @FXML
    void handleManageDeadlines(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMManageDeadlines.fxml");
    }

    @FXML
    void handleMonitorBudget(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/Samira/PMMonitorBudget.fxml");
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