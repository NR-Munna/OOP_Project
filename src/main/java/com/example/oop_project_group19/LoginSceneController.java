package com.example.oop_project_group19;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginSceneController {

    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TextField useridField;
    @javafx.fxml.FXML
    private TextField useremailField;

    private Scene scene;

    @javafx.fxml.FXML
    public void initialize() {

        ObservableList<String> userTypes = FXCollections.observableArrayList(
                "Client",
                "Freelancer",
                "Admin",
                "Project Manager",
                "HR Manager",
                "Finance Officer",
                "Technical Support",
                "Reviewer"
        );
        userTypeComboBox.setItems(userTypes);


        statusLabel.setText("");
    }

    @javafx.fxml.FXML
    public void loginbuttonOnClicked(ActionEvent actionEvent) {

        statusLabel.setText("");

        String userId = useridField.getText().trim();
        String email = useremailField.getText().trim();
        String password = passwordField.getText();
        String userType = userTypeComboBox.getValue();


        if (!validateInputs(userId, email, password, userType)) {
            return;
        }


        try {
            switchToUserScene(actionEvent, userType);
        } catch (Exception e) {
            showAlert("Error", "Failed to load the dashboard. Please try again.");
            e.printStackTrace();
        }
    }

    private boolean validateInputs(String userId, String email, String password, String userType) {

        if (userId.isEmpty() || email.isEmpty() || password.isEmpty() || userType == null) {
            statusLabel.setText("Please fill in all fields");
            showAlert("Validation Error", "Please fill in all fields before logging in.");
            return false;
        }


        if (!validateUserId(userId)) {
            statusLabel.setText("Invalid User ID format");
            showAlert("Invalid User ID", "User ID must be 6-8 digits only.");
            return false;
        }


        if (!validateEmail(email)) {
            statusLabel.setText("Invalid email format");
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }

        return true;
    }

    private boolean validateUserId(String userId) {

        return userId.matches("\\d{6,8}");
    }

    private boolean validateEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void switchToUserScene(ActionEvent event, String userType) {
        String fxmlFile = "";


        switch (userType) {
            case "Client":
                fxmlFile = "/com/example/oop_project_group19/ClientDashboard.fxml";
                break;
            case "Freelancer":
                fxmlFile = "/com/example/oop_project_group19/FreelancerDashboard.fxml";
                break;
            case "Admin":
                fxmlFile = "/com/example/oop_project_group19/AdminDashboard.fxml";
                break;
            case "Project Manager":
                fxmlFile = "/com/example/oop_project_group19/ProjectManagerDashboard.fxml";
                break;
            case "HR Manager":
                fxmlFile = "/com/example/oop_project_group19/HRManagerDashboard.fxml";
                break;
            case "Finance Officer":
                fxmlFile = "/com/example/oop_project_group19/FinanceOfficerDashboard.fxml";
                break;
            case "Technical Support":
                fxmlFile = "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml";
                break;
            case "Reviewer":
                fxmlFile = "/com/example/oop_project_group19/ReviewerDashboard.fxml";
                break;
            default:
                showAlert("Error", "Invalid user type selected.");
                return;
        }


        switchScene(event, fxmlFile);


        statusLabel.setText("Login successful! Loading dashboard...");
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Scene Loading Error", "Could not load the requested page: " + fxmlFile);
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}