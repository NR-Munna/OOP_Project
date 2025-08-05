package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TReviewsComplaintsController {

    @FXML
    private RadioButton allReviewsRadio;

    @FXML
    private Button applyFilterBtn;

    @FXML
    private RadioButton complaintsRadio;

    @FXML
    private RadioButton negativeReviewsRadio;

    @FXML
    private RadioButton positiveReviewsRadio;

    @FXML
    private TableColumn<?, ?> priorityColumn;

    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private TextArea responseNotesArea;

    @FXML
    private ComboBox<?> responseStatusCombo;

    @FXML
    private TableColumn<?, ?> reviewIdColumn;

    @FXML
    private ToggleGroup reviewTypeGroup;

    @FXML
    private TableView<?> reviewsTable;

    @FXML
    private Button saveNotesBtn;

    @FXML
    private TextArea selectedContentArea;

    @FXML
    private TextField selectedUserInfoField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> timestampColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private Button updateStatusBtn;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    void handleApplyFilter(ActionEvent event) {

    }

    @FXML
    void handleSaveNotes(ActionEvent event) {

    }

    @FXML
    void handleUpdateStatus(ActionEvent event) {

    }

}
