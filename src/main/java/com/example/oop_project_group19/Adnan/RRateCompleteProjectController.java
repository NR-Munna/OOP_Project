package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class RRateCompleteProjectController {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> clientCol;

    @FXML
    private ComboBox<?> codeQualityRating;

    @FXML
    private ComboBox<?> communicationRating;

    @FXML
    private TableView<?> completedProjectsTable;

    @FXML
    private TableColumn<?, ?> completionDateCol;

    @FXML
    private TableColumn<?, ?> freelancerCol;

    @FXML
    private ComboBox<?> overallRating;

    @FXML
    private TableColumn<?, ?> projectIdCol;

    @FXML
    private TableColumn<?, ?> projectNameCol;

    @FXML
    private ComboBox<?> requirementsRating;

    @FXML
    private TextArea reviewComments;

    @FXML
    private Button saveReviewBtn;

    @FXML
    private ComboBox<?> timelinessRating;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleSaveReview(ActionEvent event) {

    }

}
