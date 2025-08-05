package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PMEditProjectDetailsController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private TextField budgetTextField;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private ComboBox<?> clientComboBox;

    @FXML
    private TableColumn<?, ?> deadlineColumn;

    @FXML
    private DatePicker deadlineDatePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button editButton;

    @FXML
    private ComboBox<?> priorityComboBox;

    @FXML
    private TableColumn<?, ?> projectIdColumn;

    @FXML
    private TableView<?> projectsTable;

    @FXML
    private Button resetButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField skillsTextField;

    @FXML
    private TextArea specificationsTextArea;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField teamSizeTextField;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TextField titleTextField;

    @FXML
    private CheckBox urgentCheckBox;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleDelete(ActionEvent event) {

    }

    @FXML
    void handleEdit(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {

    }

}
