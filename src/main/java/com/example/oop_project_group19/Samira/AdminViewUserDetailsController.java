package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AdminViewUserDetailsController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Label joinDateLabel;

    @FXML
    private Label lastActiveLabel;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Label projectCountLabel;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TextArea userDetailsTextArea;

    @FXML
    private TableView<?> usersTable;

    @FXML
    private Button viewDetailsButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleViewDetails(ActionEvent event) {

    }

}
