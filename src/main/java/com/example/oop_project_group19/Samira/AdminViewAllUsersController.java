package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminViewAllUsersController {

    @FXML
    private Button applyFilterButton;

    @FXML
    private Button backButton;

    @FXML
    private Button clearFilterButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> joinDateColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusFilterComboBox;

    @FXML
    private Label totalUsersLabel;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private ComboBox<?> typeFilterComboBox;

    @FXML
    private TableView<?> usersTable;

    @FXML
    void handleApplyFilter(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearFilter(ActionEvent event) {

    }

}
