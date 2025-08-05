package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PMCreateScheduleController {

    @FXML
    private Button addToProjectButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private TextField budgetTextField;

    @FXML
    private Button clearFormButton;

    @FXML
    private ComboBox<?> clientComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> priorityColumn;

    @FXML
    private ComboBox<?> priorityComboBox;

    @FXML
    private TextField projectNameTextField;

    @FXML
    private ComboBox<?> projectTypeComboBox;

    @FXML
    private TableView<?> projectsTable;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    void handleAddToProject(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearForm(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

}
