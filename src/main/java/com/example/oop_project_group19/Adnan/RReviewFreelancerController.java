package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RReviewFreelancerController {

    @FXML
    private Button approveBtn;

    @FXML
    private TextArea assessmentNotes;

    @FXML
    private TableColumn<?, ?> availabilityCol;

    @FXML
    private Button backBtn;

    @FXML
    private ComboBox<?> complexityCombo;

    @FXML
    private Label currentProjectsLabel;

    @FXML
    private Button declineBtn;

    @FXML
    private TextField durationField;

    @FXML
    private TableColumn<?, ?> experienceCol;

    @FXML
    private TableColumn<?, ?> freelancerIdCol;

    @FXML
    private TableView<?> freelancersTable;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> ratingCol;

    @FXML
    private TextField requiredSkillsField;

    @FXML
    private TableColumn<?, ?> skillsCol;

    @FXML
    private Label workloadStatusLabel;

    @FXML
    void handleApproveFreelancer(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleDeclineFreelancer(ActionEvent event) {

    }

}
