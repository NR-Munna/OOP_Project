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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminViewAllUsersController implements Initializable {

    @FXML
    private Button applyFilterButton;

    @FXML
    private Button backButton;

    @FXML
    private Button clearFilterButton;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, LocalDate> joinDateColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private ComboBox<String> statusFilterComboBox;

    @FXML
    private Label totalUsersLabel;

    @FXML
    private TableColumn<User, String> typeColumn;

    @FXML
    private ComboBox<String> typeFilterComboBox;

    @FXML
    private TableView<User> usersTable;

    private Scene scene;
    private ObservableList<User> allUsers;
    private ObservableList<User> filteredUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

        statusFilterComboBox.setItems(FXCollections.observableArrayList(
                "All", "Active", "Inactive", "Suspended", "Pending"
        ));
        statusFilterComboBox.setValue("All");

        typeFilterComboBox.setItems(FXCollections.observableArrayList(
                "All", "Client", "Freelancer", "Admin", "Project Manager",
                "HR Manager", "Finance Officer", "Technical Support", "Reviewer"
        ));
        typeFilterComboBox.setValue("All");

        loadSampleData();
        filteredUsers = FXCollections.observableArrayList(allUsers);
        usersTable.setItems(filteredUsers);
        updateTotalUsersLabel();
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
    void handleApplyFilter(ActionEvent event) {
        String statusFilter = statusFilterComboBox.getValue();
        String typeFilter = typeFilterComboBox.getValue();

        filteredUsers.clear();

        for (User user : allUsers) {
            boolean matchesStatus = statusFilter.equals("All") || user.getStatus().equals(statusFilter);
            boolean matchesType = typeFilter.equals("All") || user.getType().equals(typeFilter);

            if (matchesStatus && matchesType) {
                filteredUsers.add(user);
            }
        }

        updateTotalUsersLabel();
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleClearFilter(ActionEvent event) {
        statusFilterComboBox.setValue("All");
        typeFilterComboBox.setValue("All");
        filteredUsers.clear();
        filteredUsers.addAll(allUsers);
        updateTotalUsersLabel();
    }

    private void updateTotalUsersLabel() {
        totalUsersLabel.setText("Total Users: " + filteredUsers.size());
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