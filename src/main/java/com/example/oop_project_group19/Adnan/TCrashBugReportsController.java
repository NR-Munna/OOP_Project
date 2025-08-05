package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TCrashBugReportsController {

    @FXML
    private CheckBox authenticationCheckbox;

    @FXML
    private CheckBox backendCheckbox;

    @FXML
    private RadioButton bugReportRadio;

    @FXML
    private Button clearFormBtn;

    @FXML
    private RadioButton crashReportRadio;

    @FXML
    private Button createReportBtn;

    @FXML
    private CheckBox databaseCheckbox;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField errorReasonField;

    @FXML
    private TextArea generatedReportArea;

    @FXML
    private DatePicker incidentDatePicker;

    @FXML
    private TextField incidentTimeField;

    @FXML
    private CheckBox mobileCheckbox;

    @FXML
    private CheckBox networkCheckbox;

    @FXML
    private CheckBox paymentCheckbox;

    @FXML
    private RadioButton performanceIssueRadio;

    @FXML
    private ToggleGroup reportTypeGroup;

    @FXML
    private Button saveReportBtn;

    @FXML
    private CheckBox securityCheckbox;

    @FXML
    private ComboBox<?> severityCombo;

    @FXML
    private TextArea stepsToReproduceArea;

    @FXML
    private CheckBox uiCheckbox;

    @FXML
    void handleClearForm(ActionEvent event) {

    }

    @FXML
    void handleCreateReport(ActionEvent event) {

    }

    @FXML
    void handleSaveReport(ActionEvent event) {

    }

}
