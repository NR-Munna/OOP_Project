package com.example.oop_project_group19.Samira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminUpdateUserStatusController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<User, String> currentStatusColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, LocalDate> lastUpdatedColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private ComboBox<String> newStatusComboBox;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label selectedUserLabel;

    @FXML
    private Label successLabel;

    @FXML
    private TableColumn<User, String> typeColumn;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<User> usersTable;

    private Scene scene;
    private ObservableList<User> allUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        currentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastActive"));

        newStatusComboBox.setItems(FXCollections.observableArrayList(
                "Active", "Inactive", "Suspended", "Pending"
        ));

        loadSampleData();
        usersTable.setItems(allUsers);

        clearLabels();

        usersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedUserLabel.setText("Selected User: " + newSelection.getName());
                selectedUserLabel.setVisible(true);
            } else {
                selectedUserLabel.setVisible(false);
            }
        });
    }

    private void loadSampleData() {
        allUsers = FXCollections.observableArrayList(
                new User(1, "John Doe", "Client", "Active", LocalDate.of(2024, 1, 15), "john@email.com"),
                new User(2, "Jane Smith", "Freelancer", "Active", LocalDate.of(2024, 2, 20), "jane@email.com"),
                new User(3, "Bob Wilson", "Admin", "Active", LocalDate.of(2023, 12, 10), "bob@email.com"),
                new User(4, "Alice Brown", "Project Manager", "Inactive", LocalDate.of(2024, 3, 5), "alice@email.com"),
                new User(5, "Charlie Davis", "HR Manager", "Active", LocalDate.of(2024, 1, 25), "charlie@email.com")
        );
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleCancel(ActionEvent event) {
        newStatusComboBox.setValue(null);
        reasonTextArea.clear();
        usersTable.getSelectionModel().clearSelection();
        clearLabels();
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        clearLabels();

        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        String newStatus = newStatusComboBox.getValue();
        String reason = reasonTextArea.getText().trim();

        if (selectedUser == null) {
            errorLabel.setText("Please select a user from the table.");
            errorLabel.setVisible(true);
            return;
        }

        if (newStatus == null) {
            errorLabel.setText("Please select a new status.");
            errorLabel.setVisible(true);
            return;
        }

        if (reason.isEmpty()) {
            errorLabel.setText("Please provide a reason for the status change.");
            errorLabel.setVisible(true);
            return;
        }

        if (selectedUser.getStatus().equals(newStatus)) {
            errorLabel.setText("User already has this status.");
            errorLabel.setVisible(true);
            return;
        }

        selectedUser.setStatus(newStatus);
        selectedUser.setLastActive(LocalDate.now());
        usersTable.refresh();

        successLabel.setText("Status updated successfully for " + selectedUser.getName());
        successLabel.setVisible(true);

        newStatusComboBox.setValue(null);
        reasonTextArea.clear();
    }

    private void clearLabels() {
        errorLabel.setVisible(false);
        successLabel.setVisible(false);
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