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

public class AdminViewUserDetailsController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private Label joinDateLabel;

    @FXML
    private Label lastActiveLabel;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private Label projectCountLabel;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private TableColumn<User, String> typeColumn;

    @FXML
    private TextArea userDetailsTextArea;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Button viewDetailsButton;

    private Scene scene;
    private ObservableList<User> allUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadSampleData();
        usersTable.setItems(allUsers);

        clearDetailsDisplay();
    }

    private void loadSampleData() {
        allUsers = FXCollections.observableArrayList(
                new User(1, "Ronaldo", "Client", "Active", LocalDate.of(2024, 1, 15), "cr7@email.com"),
                new User(2, "Messi", "Freelancer", "Active", LocalDate.of(2024, 2, 20), "lm10@email.com"),
                new User(3, "Neymar", "Admin", "Active", LocalDate.of(2023, 12, 10), "nmjr@email.com"),
                new User(4, "Haaland", "Project Manager", "Inactive", LocalDate.of(2024, 3, 5), "erlhlnd@email.com"),
                new User(5, " Davis", "HR Manager", "Active", LocalDate.of(2024, 1, 25), "charlie@email.com")
        );

        for (User user : allUsers) {
            user.setProjectCount((int)(Math.random() * 10) + 1);
            user.setLastActive(LocalDate.now().minusDays((long)(Math.random() * 30)));
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        usersTable.refresh();
        clearDetailsDisplay();
    }

    @FXML
    void handleViewDetails(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            userDetailsTextArea.setText("Please select a user from the table to view details.");
            return;
        }

        displayUserDetails(selectedUser);
    }

    private void displayUserDetails(User user) {
        joinDateLabel.setText("Join Date: " + user.getJoinDate().toString());
        lastActiveLabel.setText("Last Active: " + user.getLastActive().toString());
        projectCountLabel.setText("Projects: " + user.getProjectCount());

        StringBuilder details = new StringBuilder();
        details.append("User ID: ").append(user.getId()).append("\n");
        details.append("Name: ").append(user.getName()).append("\n");
        details.append("Type: ").append(user.getType()).append("\n");
        details.append("Status: ").append(user.getStatus()).append("\n");
        details.append("Email: ").append(user.getEmail()).append("\n");
        details.append("Join Date: ").append(user.getJoinDate()).append("\n");
        details.append("Last Active: ").append(user.getLastActive()).append("\n");
        details.append("Project Count: ").append(user.getProjectCount()).append("\n");

        userDetailsTextArea.setText(details.toString());
    }

    private void clearDetailsDisplay() {
        joinDateLabel.setText("Join Date: -");
        lastActiveLabel.setText("Last Active: -");
        projectCountLabel.setText("Projects: -");
        userDetailsTextArea.setText("Select a user and click 'View Details' to see user information.");
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