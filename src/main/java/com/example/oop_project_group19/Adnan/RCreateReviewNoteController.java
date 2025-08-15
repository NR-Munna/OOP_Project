package com.example.oop_project_group19.Adnan;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

public class RCreateReviewNoteController {

    @FXML private Button addNoteBtn;
    @FXML private Button backBtn;
    @FXML private RadioButton freelancerRadio;
    @FXML private ComboBox<String> noteCategoryCombo;
    @FXML private ComboBox<String> priorityCombo;
    @FXML private RadioButton projectRadio;
    @FXML private TextArea recommendationsArea;
    @FXML private TextArea reviewNotesArea;
    @FXML private ComboBox<String> targetSelection;

    private Scene scene;

    public void initialize() {
        noteCategoryCombo.setItems(FXCollections.observableArrayList("Performance", "Quality", "Communication", "Timeliness", "Skills", "Other"));
        priorityCombo.setItems(FXCollections.observableArrayList("Low", "Medium", "High", "Critical"));

        projectRadio.setSelected(true);

        projectRadio.setOnAction(e -> updateTargetSelection());
        freelancerRadio.setOnAction(e -> updateTargetSelection());

        updateTargetSelection();
    }

    private void updateTargetSelection() {
        if (projectRadio.isSelected()) {
            targetSelection.setItems(FXCollections.observableArrayList("Project P001 - E-commerce Website", "Project P002 - Mobile App", "Project P003 - Data Analysis", "Project P004 - Logo Design"));
        } else if (freelancerRadio.isSelected()) {
            targetSelection.setItems(FXCollections.observableArrayList("Adnan Freelancer", "Naimur Developer", "Samira Designer", "Shawon Coder", "Mike Programmer"));
        }
        targetSelection.setValue(null);
    }

    @FXML
    void handleAddNote(ActionEvent event) {
        String targetType = projectRadio.isSelected() ? "Project" : "Freelancer";
        String target = targetSelection.getValue();
        String category = noteCategoryCombo.getValue();
        String priority = priorityCombo.getValue();
        String notes = reviewNotesArea.getText();
        String recommendations = recommendationsArea.getText();

        if (target != null && category != null && priority != null && !notes.isEmpty()) {
            String notePreview = "NOTE ADDED:\n" +
                    "Target Type: " + targetType + "\n" +
                    "Target: " + target + "\n" +
                    "Category: " + category + "\n" +
                    "Priority: " + priority + "\n" +
                    "Notes: " + notes + "\n" +
                    "Recommendations: " + recommendations + "\n" +
                    "Added on: " + java.time.LocalDateTime.now();

            reviewNotesArea.setText(notePreview);
            recommendationsArea.clear();
            targetSelection.setValue(null);
            noteCategoryCombo.setValue(null);
            priorityCombo.setValue(null);
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
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