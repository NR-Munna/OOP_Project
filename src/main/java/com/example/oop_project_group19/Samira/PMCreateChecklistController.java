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

public class PMCreateChecklistController {

    @FXML
    private Button addTaskButton;

    @FXML
    private TableColumn<?, ?> assignedColumn;

    @FXML
    private ComboBox<?> assignedToComboBox;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> checklistTable;

    @FXML
    private Button clearFormButton;

    @FXML
    private Button createChecklistButton;

    @FXML
    private CheckBox criticalTaskCheckBox;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private Button doneButton;

    @FXML
    private TableColumn<?, ?> dueDateColumn;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Button editTaskButton;

    @FXML
    private TextField estimatedHoursTextField;

    @FXML
    private TableColumn<?, ?> hoursColumn;

    @FXML
    private TableColumn<?, ?> priorityColumn;

    @FXML
    private ComboBox<?> priorityComboBox;

    @FXML
    private ComboBox<?> projectComboBox;

    @FXML
    private Button removeTaskButton;

    @FXML
    private Label selectedProjectLabel;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField taskDescriptionTextField;

    @FXML
    private TableColumn<?, ?> taskIdColumn;

    @FXML
    private TextArea taskNotesTextArea;

    @FXML
    void handleAddTask(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearForm(ActionEvent event) {

    }

    @FXML
    void handleCreateChecklist(ActionEvent event) {

    }

    @FXML
    void handleDone(ActionEvent event) {

    }

    @FXML
    void handleEditTask(ActionEvent event) {

    }

    @FXML
    void handleRemoveTask(ActionEvent event) {

    }

}
