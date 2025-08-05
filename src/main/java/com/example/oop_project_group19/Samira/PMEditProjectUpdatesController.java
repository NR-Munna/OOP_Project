package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class PMEditProjectUpdatesController {

    @FXML
    private TableColumn<?, ?> assignedToColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> currentStatusColumn;

    @FXML
    private TableColumn<?, ?> deadlineColumn;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private CheckBox notifyClientCheckBox;

    @FXML
    private CheckBox notifyTeamCheckBox;

    @FXML
    private Button previewButton;

    @FXML
    private ComboBox<?> priorityComboBox;

    @FXML
    private TableColumn<?, ?> progressColumn;

    @FXML
    private Label progressLabel;

    @FXML
    private Slider progressSlider;

    @FXML
    private TableColumn<?, ?> projectIdColumn;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private TableView<?> projectsTable;

    @FXML
    private ComboBox<?> qualityRatingComboBox;

    @FXML
    private Button resetButton;

    @FXML
    private Label selectedProjectLabel;

    @FXML
    private ComboBox<?> statusComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private Button updateButton;

    @FXML
    private CheckBox updateTimestampCheckBox;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handlePreview(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {

    }

    @FXML
    void handleUpdate(ActionEvent event) {

    }

}
