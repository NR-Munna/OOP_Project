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

public class PostJobController {

    @FXML
    private TextField budgetField;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private TextArea jobDescriptionArea;

    @FXML
    private TextField jobTitleField;

    @FXML
    private Button postJobButton;

    @FXML
    private ComboBox<String> projectTypeCombo;

    @FXML
    private CheckBox remoteCheckBox;

    @FXML
    private TextField skillsField;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox urgentCheckBox;

    private Scene scene;

    @FXML
    public void initialize() {
        projectTypeCombo.getItems().addAll(
                "Web Development",
                "Mobile App",
                "Graphic Design",
                "Content Writing",
                "Digital Marketing",
                "Data Analysis"
        );
    }

    @FXML
    void handleCancel(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handlePostJob(ActionEvent event) {
        String title = jobTitleField.getText();
        String description = jobDescriptionArea.getText();
        String skills = skillsField.getText();
        String budget = budgetField.getText();

        if (title.isEmpty() || description.isEmpty() || skills.isEmpty() ||
                budget.isEmpty() || deadlinePicker.getValue() == null) {
            statusLabel.setText("Please fill all required fields");
            return;
        }

        try {
            double budgetValue = Double.parseDouble(budget);
            if (budgetValue <= 0) {
                statusLabel.setText("Budget must be positive");
                return;
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid budget format");
            return;
        }

        statusLabel.setText("Job posted successfully!");

        jobTitleField.clear();
        jobDescriptionArea.clear();
        skillsField.clear();
        budgetField.clear();
        deadlinePicker.setValue(null);
        projectTypeCombo.getSelectionModel().clearSelection();
        remoteCheckBox.setSelected(false);
        urgentCheckBox.setSelected(false);
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