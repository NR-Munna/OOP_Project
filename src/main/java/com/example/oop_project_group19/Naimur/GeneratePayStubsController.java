package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GeneratePayStubsController {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> freelancerIdColumn;

    @FXML
    private TableColumn<?, ?> freelancerNameColumn;

    @FXML
    private TableView<?> freelancerTable;

    @FXML
    private Button generatePayStubBtn;

    @FXML
    private TextField hourlyRateField;

    @FXML
    private TextArea payStubDisplayArea;

    @FXML
    private TextField paymentAmountField;

    @FXML
    private DatePicker paymentDatePicker;

    @FXML
    private TextField projectNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField selectedFreelancerField;

    @FXML
    private TableColumn<?, ?> skillsColumn;

    @FXML
    private TextField workHoursField;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onGeneratePayStub(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {

    }

}
