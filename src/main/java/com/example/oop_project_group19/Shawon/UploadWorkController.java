package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UploadWorkController {

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private Button backButton;

    @FXML
    private TextField clientNameField;

    @FXML
    private TextArea commentsArea;

    @FXML
    private TextField deadlineField;

    @FXML
    private TableColumn<?, ?> fileNameColumn;

    @FXML
    private TableColumn<?, ?> fileSizeColumn;

    @FXML
    private TableColumn<?, ?> fileTypeColumn;

    @FXML
    private TableView<?> filesTable;

    @FXML
    private CheckBox finalSubmissionCheckBox;

    @FXML
    private ComboBox<?> projectComboBox;

    @FXML
    private CheckBox revisionCheckBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button selectFilesButton;

    @FXML
    private Label selectedFilesLabel;

    @FXML
    private TextField statusField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitWorkButton;

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
    void handleSave(ActionEvent event) {

    }

    @FXML
    void handleSelectFiles(ActionEvent event) {

    }

    @FXML
    void handleSubmitWork(ActionEvent event) {

    }

}
