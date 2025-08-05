package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PostJobController {

    @FXML
    private TextField budgetField;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private TextArea jobDescriptionArea;

    @FXML
    private TextField jobTitleField;

    @FXML
    private Button postJobButton;

    @FXML
    private ComboBox<?> projectTypeCombo;

    @FXML
    private CheckBox remoteCheckBox;

    @FXML
    private TextField skillsField;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox urgentCheckBox;

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handlePostJob(ActionEvent event) {

    }

}
