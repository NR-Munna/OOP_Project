package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class ProvideFeedbackController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> communicationRatingCombo;

    @FXML
    private ComboBox<Project> completedProjectCombo;

    @FXML
    private TextField completionDateField;

    @FXML
    private TextField freelancerNameField;

    @FXML
    private TextField projectDurationField;

    @FXML
    private TextField projectTitleField;

    @FXML
    private CheckBox publicReviewCheckBox;

    @FXML
    private ComboBox<String> qualityRatingCombo;

    @FXML
    private RadioButton rating1Radio;

    @FXML
    private RadioButton rating2Radio;

    @FXML
    private RadioButton rating3Radio;

    @FXML
    private RadioButton rating4Radio;

    @FXML
    private RadioButton rating5Radio;

    @FXML
    private ToggleGroup ratingGroup;

    @FXML
    private CheckBox recommendCheckBox;

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitFeedbackButton;

    @FXML
    private ComboBox<String> timelinessRatingCombo;

    private Scene scene;

    @FXML
    public void initialize() {
        completedProjectCombo.getItems().addAll(
                new Project("P1", "Website Development", "C1", "F1", "Shakib Khan", null, null, 1500.0, "Completed"),
                new Project("P2", "Logo Design", "C1", "F2", "Ananta Jalil", null, null, 500.0, "Completed")
        );

        String[] ratings = {"1 - Poor", "2 - Fair", "3 - Good", "4 - Very Good", "5 - Excellent"};
        communicationRatingCombo.getItems().addAll(ratings);
        qualityRatingCombo.getItems().addAll(ratings);
        timelinessRatingCombo.getItems().addAll(ratings);

        completionDateField.setEditable(false);
        freelancerNameField.setEditable(false);
        projectDurationField.setEditable(false);
        projectTitleField.setEditable(false);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleProjectSelection(ActionEvent event) {
        Project selectedProject = completedProjectCombo.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            projectTitleField.setText(selectedProject.getTitle());
            freelancerNameField.setText(selectedProject.getFreelancerName());
            completionDateField.setText("2024-01-15");
            projectDurationField.setText("30 days");

            statusLabel.setText("Project selected for feedback");
        }
    }

    @FXML
    void handleSubmitFeedback(ActionEvent event) {
        if (completedProjectCombo.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select a completed project");
            return;
        }

        if (ratingGroup.getSelectedToggle() == null) {
            statusLabel.setText("Please select an overall rating (1-5 stars)");
            return;
        }

        String reviewText = reviewTextArea.getText().trim();
        if (reviewText.isEmpty()) {
            statusLabel.setText("Please provide feedback text");
            return;
        }

        if (reviewText.length() < 10) {
            statusLabel.setText("Feedback text too short (minimum 10 characters)");
            return;
        }

        RadioButton selectedRating = (RadioButton) ratingGroup.getSelectedToggle();
        statusLabel.setText("Feedback submitted successfully! Rating: " + selectedRating.getText() +
                " - Freelancer notified.");

        clearForm();
    }

    private void clearForm() {
        reviewTextArea.clear();
        ratingGroup.selectToggle(null);
        communicationRatingCombo.getSelectionModel().clearSelection();
        qualityRatingCombo.getSelectionModel().clearSelection();
        timelinessRatingCombo.getSelectionModel().clearSelection();
        publicReviewCheckBox.setSelected(false);
        recommendCheckBox.setSelected(false);
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