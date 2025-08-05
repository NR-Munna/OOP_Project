package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PMManageDeadlinesController {

    @FXML
    private TableColumn<?, ?> assignedToColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<?, ?> currentDeadlineColumn;

    @FXML
    private TableColumn<?, ?> daysLeftColumn;

    @FXML
    private TableView<?> deadlinesTable;

    @FXML
    private ComboBox<?> filterComboBox;

    @FXML
    private DatePicker newDeadlineDatePicker;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private CheckBox notifyTeamCheckBox;

    @FXML
    private TableColumn<?, ?> priorityColumn;

    @FXML
    private TableColumn<?, ?> projectIdColumn;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private TextField reasonTextField;

    @FXML
    private Button refreshButton;

    @FXML
    private Label selectedProjectLabel;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Button updateDeadlineButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleUpdateDeadline(ActionEvent event) {

    }

}
