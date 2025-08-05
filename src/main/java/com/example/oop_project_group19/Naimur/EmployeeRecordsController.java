package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EmployeeRecordsController {

    @FXML
    private RadioButton activeRadio;

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<?> departmentComboBox;

    @FXML
    private Button editRecordBtn;

    @FXML
    private ComboBox<?> employeeComboBox;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField fullNameField;

    @FXML
    private DatePicker hireDatePicker;

    @FXML
    private RadioButton inactiveRadio;

    @FXML
    private TextArea notesArea;

    @FXML
    private TextField positionField;

    @FXML
    private TextField salaryField;

    @FXML
    private Button saveBtn;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onEditRecord(ActionEvent event) {

    }

    @FXML
    void onEmployeeSelected(ActionEvent event) {

    }

    @FXML
    void onSaveRecord(ActionEvent event) {

    }

}
