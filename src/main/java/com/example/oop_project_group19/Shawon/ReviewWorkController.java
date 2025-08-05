package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class ReviewWorkController {

    @FXML
    private TableColumn<?, ?> actionsColumn;

    @FXML
    private RadioButton approveRadio;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> fileNameColumn;

    @FXML
    private TableColumn<?, ?> fileSizeColumn;

    @FXML
    private TableColumn<?, ?> fileTypeColumn;

    @FXML
    private TableView<?> filesTable;

    @FXML
    private TextArea freelancerCommentsArea;

    @FXML
    private ComboBox<?> projectComboBox;

    @FXML
    private Button refreshButton;

    @FXML
    private RadioButton rejectRadio;

    @FXML
    private RadioButton requestChangesRadio;

    @FXML
    private TextArea reviewCommentsArea;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitReviewButton;

    @FXML
    private TableColumn<?, ?> uploadDateColumn;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @Deprecated
    void handleDownloadAll(ActionEvent event) {

    }

    @FXML
    void handleProjectSelection(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSubmitReview(ActionEvent event) {

    }

}
