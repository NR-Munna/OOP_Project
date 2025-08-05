package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminSearchUsersController {

    @FXML
    private Button backButton;

    @FXML
    private Button clearSearchButton;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Label noResultsLabel;

    @FXML
    private Label resultCountLabel;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<?> searchResultsTable;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<?> searchTypeComboBox;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearSearch(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

}
