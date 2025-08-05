package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class RCheckMilestoneController {

    @FXML
    private TableView<?> activeProjectsTable;

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> deadlineCol;

    @FXML
    private TableColumn<?, ?> deliverableCol;

    @FXML
    private TableColumn<?, ?> dueDateCol;

    @FXML
    private TableColumn<?, ?> freelancerCol;

    @FXML
    private TextArea milestoneComments;

    @FXML
    private TableColumn<?, ?> milestoneNameCol;

    @FXML
    private TableView<?> milestonesTable;

    @FXML
    private TableColumn<?, ?> notesCol;

    @FXML
    private TableColumn<?, ?> progressCol;

    @FXML
    private TableColumn<?, ?> projectIdCol;

    @FXML
    private TableColumn<?, ?> projectNameCol;

    @FXML
    private Button requestRevisionBtn;

    @FXML
    private TableColumn<?, ?> startDateCol;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    void handleApproveMilestone(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleRequestRevision(ActionEvent event) {

    }

}
