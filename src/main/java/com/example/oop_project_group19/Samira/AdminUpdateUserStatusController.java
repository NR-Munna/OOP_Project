package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AdminUpdateUserStatusController {

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<?, ?> currentStatusColumn;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> lastUpdatedColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private ComboBox<?> newStatusComboBox;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label selectedUserLabel;

    @FXML
    private Label successLabel;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<?> usersTable;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleUpdate(ActionEvent event) {

    }

}
