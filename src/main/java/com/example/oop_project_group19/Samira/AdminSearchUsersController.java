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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminSearchUsersController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button clearSearchButton;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private Label noResultsLabel;

    @FXML
    private Label resultCountLabel;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<User> searchResultsTable;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<String> searchTypeComboBox;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private TableColumn<User, String> typeColumn;

    private Scene scene;
    private ObservableList<User> allUsers;
    private ObservableList<User> searchResults;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        searchTypeComboBox.setItems(FXCollections.observableArrayList(
                "Name", "ID", "Email"
        ));
        searchTypeComboBox.setValue("Name");

        loadSampleData();
        searchResults = FXCollections.observableArrayList();
        searchResultsTable.setItems(searchResults);

        clearLabels();
    }

    private void loadSampleData() {
        allUsers = FXCollections.observableArrayList(
                new User(1, "Shahnewaz Shawon", "Client", "Active", LocalDate.of(2024, 1, 15), "shawon@email.com"),
                new User(2, "Samira Fatema", "Freelancer", "Active", LocalDate.of(2024, 2, 20), "samira@email.com"),
                new User(3, "Naimur Rahman", "Admin", "Active", LocalDate.of(2023, 12, 10), "naimur@email.com"),
                new User(4, "Abir Hossain", "Project Manager", "Inactive", LocalDate.of(2024, 3, 5), "abir@email.com"),
                new User(5, "ABCD", "HR Manager", "Active", LocalDate.of(2024, 1, 25), "charlie@email.com")
        );
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleClearSearch(ActionEvent event) {
        searchTextField.clear();
        searchResults.clear();
        clearLabels();
    }

    @FXML
    void handleSearch(ActionEvent event) {
        clearLabels();

        String searchQuery = searchTextField.getText().trim();
        if (searchQuery.isEmpty()) {
            errorLabel.setText("Please enter search criteria");
            errorLabel.setVisible(true);
            return;
        }

        String searchType = searchTypeComboBox.getValue();
        searchResults.clear();

        for (User user : allUsers) {
            boolean matches = false;

            switch (searchType) {
                case "Name":
                    matches = user.getName().toLowerCase().contains(searchQuery.toLowerCase());
                    break;
                case "ID":
                    matches = String.valueOf(user.getId()).contains(searchQuery);
                    break;
                case "Email":
                    matches = user.getEmail().toLowerCase().contains(searchQuery.toLowerCase());
                    break;
            }

            if (matches) {
                searchResults.add(user);
            }
        }

        if (searchResults.isEmpty()) {
            noResultsLabel.setText("No results found");
            noResultsLabel.setVisible(true);
        } else {
            resultCountLabel.setText("Found " + searchResults.size() + " result(s)");
            resultCountLabel.setVisible(true);
        }
    }

    private void clearLabels() {
        errorLabel.setVisible(false);
        noResultsLabel.setVisible(false);
        resultCountLabel.setVisible(false);
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