package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    @FXML private Button addPortfolioButton;
    @FXML private Button addSkillButton;
    @FXML private ComboBox<String> availabilityCombo;
    @FXML private CheckBox availableForHireCheckBox;
    @FXML private Button backButton;
    @FXML private TextArea bioArea;
    @FXML private Button cancelButton;
    @FXML private TextField educationField;
    @FXML private TextField emailField;
    @FXML private CheckBox emailNotificationCheckBox;
    @FXML private ComboBox<String> experienceLevelCombo;
    @FXML private TextField fullNameField;
    @FXML private TextField hourlyRateField;
    @FXML private TextField languagesField;
    @FXML private TextField locationField;
    @FXML private TextField phoneField;
    @FXML private TextField portfolioField;
    @FXML private Button previewButton;
    @FXML private TextField professionalTitleField;
    @FXML private CheckBox publicProfileCheckBox;
    @FXML private Button saveProfileButton;
    @FXML private TextField skillsField;
    @FXML private Label statusLabel;

    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        availabilityCombo.setItems(FXCollections.observableArrayList(
                "Available", "Busy", "Not Available", "Available in 1 week", "Available in 1 month"
        ));

        experienceLevelCombo.setItems(FXCollections.observableArrayList(
                "Entry Level", "Intermediate", "Expert", "Senior Expert"
        ));

        loadCurrentProfile();

        availableForHireCheckBox.setSelected(true);
        emailNotificationCheckBox.setSelected(true);
        publicProfileCheckBox.setSelected(true);
    }

    private void loadCurrentProfile() {
        fullNameField.setText("John Freelancer");
        emailField.setText("john.freelancer@email.com");
        phoneField.setText("+1234567890");
        locationField.setText("New York, USA");
        professionalTitleField.setText("Full Stack Developer");
        hourlyRateField.setText("50.00");
        skillsField.setText("Java, JavaScript, React, Node.js, MySQL");
        languagesField.setText("English (Native), Spanish (Intermediate)");
        educationField.setText("Computer Science, University of Technology");
        portfolioField.setText("https://johnfreelancer.portfolio.com");
        bioArea.setText("Experienced full-stack developer with 5+ years of experience in web development. " +
                "Passionate about creating efficient and scalable solutions.");

        availabilityCombo.setValue("Available");
        experienceLevelCombo.setValue("Expert");
    }

    @FXML
    void handleAddPortfolio(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Portfolio Link");
        dialog.setHeaderText("Enter Portfolio URL");
        dialog.setContentText("URL:");

        dialog.showAndWait().ifPresent(url -> {
            String currentPortfolio = portfolioField.getText();
            if (currentPortfolio.isEmpty()) {
                portfolioField.setText(url);
            } else {
                portfolioField.setText(currentPortfolio + ", " + url);
            }
            statusLabel.setText("Portfolio link added");
        });
    }

    @FXML
    void handleAddSkill(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Skill");
        dialog.setHeaderText("Enter New Skill");
        dialog.setContentText("Skill:");

        dialog.showAndWait().ifPresent(skill -> {
            String currentSkills = skillsField.getText();
            if (currentSkills.isEmpty()) {
                skillsField.setText(skill);
            } else {
                skillsField.setText(currentSkills + ", " + skill);
            }
            statusLabel.setText("Skill added");
        });
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleCancel(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Cancel Changes");
        confirmation.setHeaderText("Discard Changes");
        confirmation.setContentText("Are you sure you want to discard all changes?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
        }
    }

    @FXML
    void handlePreview(ActionEvent event) {
        Alert preview = new Alert(Alert.AlertType.INFORMATION);
        preview.setTitle("Profile Preview");
        preview.setHeaderText(fullNameField.getText() + " - " + professionalTitleField.getText());

        String previewContent = "Location: " + locationField.getText() + "\n" +
                "Hourly Rate: $" + hourlyRateField.getText() + "\n" +
                "Experience: " + experienceLevelCombo.getValue() + "\n" +
                "Availability: " + availabilityCombo.getValue() + "\n\n" +
                "Bio: " + bioArea.getText();

        preview.setContentText(previewContent);
        preview.showAndWait();
    }

    @FXML
    void handleSaveProfile(ActionEvent event) {
        if (validateProfile()) {
            Freelancer freelancer = new Freelancer("FL001", fullNameField.getText(),
                    emailField.getText(), skillsField.getText(),
                    Float.parseFloat(hourlyRateField.getText()));

            freelancer.setAvailable(availableForHireCheckBox.isSelected());

            statusLabel.setText("Profile updated successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success");
            success.setHeaderText("Profile Updated");
            success.setContentText("Your profile has been updated successfully!");
            success.showAndWait();
        }
    }

    private boolean validateProfile() {
        if (fullNameField.getText().trim().isEmpty()) {
            statusLabel.setText("Full name is required");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (emailField.getText().trim().isEmpty() || !emailField.getText().contains("@")) {
            statusLabel.setText("Valid email is required");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (professionalTitleField.getText().trim().isEmpty()) {
            statusLabel.setText("Professional title is required");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        try {
            float rate = Float.parseFloat(hourlyRateField.getText());
            if (rate <= 0) {
                statusLabel.setText("Hourly rate must be greater than 0");
                statusLabel.setStyle("-fx-text-fill: red;");
                return false;
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid hourly rate format");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (skillsField.getText().trim().isEmpty()) {
            statusLabel.setText("At least one skill is required");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (bioArea.getText().length() < 50) {
            statusLabel.setText("Bio must be at least 50 characters");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (experienceLevelCombo.getValue() == null) {
            statusLabel.setText("Please select experience level");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (availabilityCombo.getValue() == null) {
            statusLabel.setText("Please select availability status");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        return true;
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