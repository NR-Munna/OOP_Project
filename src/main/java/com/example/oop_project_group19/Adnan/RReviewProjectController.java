package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class RReviewProjectController {

    @FXML
    private Button approveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> clientNameCol;

    @FXML
    private TextArea commentsArea;

    @FXML
    private TableColumn<?, ?> projectIdCol;

    @FXML
    private TableView<?> projectTable;

    @FXML
    private TableColumn<?, ?> projectTitleCol;

    @FXML
    private Button rejectBtn;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableColumn<?, ?> submissionDateCol;

    @FXML
    void handleApprove(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleReject(ActionEvent event) {

    }

}
