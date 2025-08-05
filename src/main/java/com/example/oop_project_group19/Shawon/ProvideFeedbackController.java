package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ProvideFeedbackController {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> communicationRatingCombo;

    @FXML
    private ComboBox<?> completedProjectCombo;

    @FXML
    private TextField completionDateField;

    @FXML
    private TextField freelancerNameField;

    @FXML
    private TextField projectDurationField;

    @FXML
    private TextField projectTitleField;

    @FXML
    private CheckBox publicReviewCheckBox;

    @FXML
    private ComboBox<?> qualityRatingCombo;

    @FXML
    private RadioButton rating1Radio;

    @FXML
    private RadioButton rating2Radio;

    @FXML
    private RadioButton rating3Radio;

    @FXML
    private RadioButton rating4Radio;

    @FXML
    private RadioButton rating5Radio;

    @FXML
    private ToggleGroup ratingGroup;

    @FXML
    private CheckBox recommendCheckBox;

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitFeedbackButton;

    @FXML
    private ComboBox<?> timelinessRatingCombo;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleProjectSelection(ActionEvent event) {

    }

    @Deprecated
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSubmitFeedback(ActionEvent event) {

    }

}
