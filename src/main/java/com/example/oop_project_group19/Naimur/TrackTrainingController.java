package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TrackTrainingController {

    @FXML
    private Button backBtn;

    @FXML
    private Button completeBtn;

    @FXML
    private TableColumn<?, ?> courseColumn;

    @FXML
    private TableColumn<?, ?> freelancerColumn;

    @FXML
    private TableColumn<?, ?> progressColumn;

    @FXML
    private Label selectedCourseLabel;

    @FXML
    private Label selectedFreelancerLabel;

    @FXML
    private Label selectedProgressLabel;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private CheckBox sufficientCheckBox;

    @FXML
    private TableView<?> trainingTable;

    @FXML
    private Button updateBtn;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onCompleteTraining(ActionEvent event) {

    }

    @Deprecated
    void onRefresh(ActionEvent event) {

    }

    @FXML
    void onUpdateProgress(ActionEvent event) {

    }

}
