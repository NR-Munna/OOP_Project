package com.example.oop_project_group19.Adnan;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TUserActivityMonitorController {

    @FXML private TextArea activityLogArea;
    @FXML private TableView<UserActivity> activityTable;
    @FXML private ComboBox<String> activityTypeCombo;
    @FXML private TextField alertMessageField;
    @FXML private ComboBox<String> alertTypeCombo;
    @FXML private Button backBtn;
    @FXML private Button blockUserBtn;
    @FXML private TableColumn<UserActivity, String> emailColumn;
    @FXML private TableColumn<UserActivity, Integer> failedAttemptsColumn;
    @FXML private TableColumn<UserActivity, String> ipAddressColumn;
    @FXML private TableColumn<UserActivity, LocalDateTime> lastAttemptColumn;
    @FXML private Button resetAttemptsBtn;
    @FXML private TextField selectedFailedAttemptsField;
    @FXML private TextField selectedUserIdField;
    @FXML private TextField selectedUsernameField;
    @FXML private Button sendAlertBtn;
    @FXML private TableColumn<UserActivity, String> statusColumn;
    @FXML private TableColumn<UserActivity, String> userIdColumn;
    @FXML private TableColumn<UserActivity, String> usernameColumn;

    private Scene scene;
    private ObservableList<UserActivity> activityList = FXCollections.observableArrayList();

    public void initialize() {
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        failedAttemptsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFailedAttempts()).asObject());
        lastAttemptColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastAttempt()));
        ipAddressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIpAddress()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        activityTypeCombo.setItems(FXCollections.observableArrayList("All", "High Risk", "Blocked", "Active"));
        alertTypeCombo.setItems(FXCollections.observableArrayList("Warning", "Security Alert", "Account Suspension"));

        loadSampleData();
        activityTable.setItems(activityList);

        activityTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedUserIdField.setText(newSelection.getUserId());
                selectedUsernameField.setText(newSelection.getUsername());
                selectedFailedAttemptsField.setText(String.valueOf(newSelection.getFailedAttempts()));

                activityLogArea.setText("User Activity Log for: " + newSelection.getUsername() + "\n" +
                        "User ID: " + newSelection.getUserId() + "\n" +
                        "Email: " + newSelection.getEmail() + "\n" +
                        "Failed Login Attempts: " + newSelection.getFailedAttempts() + "\n" +
                        "Last Attempt: " + newSelection.getLastAttempt() + "\n" +
                        "IP Address: " + newSelection.getIpAddress() + "\n" +
                        "Status: " + newSelection.getStatus());
            }
        });
    }

    private void loadSampleData() {
        activityList.add(new UserActivity("U001", "will_ams", "williams@email.com", 5, "192.168.1.1"));
        activityList.add(new UserActivity("U002", "jane_smith", "jane@email.com", 8, "192.168.1.2"));
        activityList.add(new UserActivity("U003", "bob_johnson", "bob@email.com", 12, "192.168.1.3"));
        activityList.add(new UserActivity("U004", "alice_brown", "alice@email.com", 3, "192.168.1.4"));
    }

    @FXML
    void handleBlockUser(ActionEvent event) {
        UserActivity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Blocked");
            activityTable.refresh();
            activityLogArea.appendText("\n\nUser " + selected.getUsername() + " has been blocked.");
            System.out.println("User blocked: " + selected.getUsername());
        }
    }

    @FXML
    void handleResetAttempts(ActionEvent event) {
        UserActivity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setFailedAttempts(0);
            selected.setStatus("Active");
            activityTable.refresh();
            selectedFailedAttemptsField.setText("0");
            activityLogArea.appendText("\n\nFailed attempts reset for user: " + selected.getUsername());
            System.out.println("Failed attempts reset for: " + selected.getUsername());
        }
    }

    @FXML
    void handleSendAlert(ActionEvent event) {
        UserActivity selected = activityTable.getSelectionModel().getSelectedItem();
        String alertType = alertTypeCombo.getValue();
        String message = alertMessageField.getText();

        if (selected != null && alertType != null && !message.isEmpty()) {
            activityLogArea.appendText("\n\nAlert sent to " + selected.getUsername() +
                    "\nType: " + alertType + "\nMessage: " + message);
            alertMessageField.clear();
            System.out.println("Alert sent to: " + selected.getUsername());
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
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