package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HireFreelancerController {

    @FXML
    private TextField bidAmountField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmHireButton;

    @FXML
    private DatePicker deliveryDeadlinePicker;

    @FXML
    private ToggleGroup durationGroup;

    @FXML
    private RadioButton fixedTermRadio;

    @FXML
    private TextField freelancerNameField;

    @FXML
    private RadioButton hourlyRadio;

    @FXML
    private TextArea instructionsArea;

    @FXML
    private CheckBox milestonePaymentCheckBox;

    @FXML
    private CheckBox nondisclosureCheckBox;

    @FXML
    private RadioButton ongoingRadio;

    @FXML
    private ComboBox<?> paymentMethodCombo;

    @FXML
    private TextField projectTitleField;

    @FXML
    private Label statusLabel;

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleConfirmHire(ActionEvent event) {

    }

}
