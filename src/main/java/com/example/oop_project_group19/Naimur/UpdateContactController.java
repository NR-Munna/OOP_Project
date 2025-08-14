package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateContactController {

    @FXML
    private TextArea addressArea;

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private Label validationLabel;

    private Scene scene;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[+]?[0-9-\\s()]{10,15}$");

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onCancel(ActionEvent event) {
        clearFields();
        validationLabel.setText("");
    }

    @FXML
    void onSaveContact(ActionEvent event) {
        if (validateInput()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contact Updated");
            alert.setHeaderText("Success");
            alert.setContentText("Contact information updated for " + nameField.getText());
            alert.showAndWait();

            clearFields();
            validationLabel.setText("Contact information saved successfully");
            validationLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    void onSearchFreelancer(ActionEvent event) {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            validationLabel.setText("Please enter a name to search");
            validationLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        nameField.setText(searchText);
        emailField.setText(searchText.toLowerCase().replace(" ", ".") + "@email.com");
        phoneField.setText("+1-555-0123");
        addressArea.setText("123 Main Street\nCity, State 12345");

        validationLabel.setText("Freelancer found and loaded");
        validationLabel.setStyle("-fx-text-fill: green;");
    }

    private boolean validateInput() {
        if (nameField.getText().trim().isEmpty()) {
            validationLabel.setText("Name field is required");
            validationLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            validationLabel.setText("Email field is required");
            validationLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            validationLabel.setText("Please enter a valid email address");
            validationLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            validationLabel.setText("Phone field is required");
            validationLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            validationLabel.setText("Please enter a valid phone number");
            validationLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        return true;
    }

    private void clearFields() {
        searchField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressArea.clear();
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