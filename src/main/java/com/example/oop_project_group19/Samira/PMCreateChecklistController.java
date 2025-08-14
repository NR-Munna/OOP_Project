package com.example.oop_project_group19.Samira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PMCreateChecklistController implements Initializable {

    @FXML private Button addTaskButton;
    @FXML private TableColumn<Task, String> assignedColumn;
    @FXML private ComboBox<String> assignedToComboBox;
    @FXML private Button backButton;
    @FXML private TableView<Task> checklistTable;
    @FXML private Button clearFormButton;
    @FXML private Button createChecklistButton;
    @FXML private CheckBox criticalTaskCheckBox;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private Button doneButton;
    @FXML private TableColumn<Task, LocalDate> dueDateColumn;
    @FXML private DatePicker dueDatePicker;
    @FXML private Button editTaskButton;
    @FXML private TextField estimatedHoursTextField;
    @FXML private TableColumn<Task, Float> hoursColumn;
    @FXML private TableColumn<Task, String> priorityColumn;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private ComboBox<String> projectComboBox;
    @FXML private Button removeTaskButton;
    @FXML private Label selectedProjectLabel;
    @FXML private TableColumn<Task, String> statusColumn;
    @FXML private Label statusLabel;
    @FXML private TextField taskDescriptionTextField;
    @FXML private TableColumn<Task, Integer> taskIdColumn;
    @FXML private TextArea taskNotesTextArea;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    private int nextTaskId = 1;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        setupComboBoxes();
        addSampleData();

        projectComboBox.setOnAction(event -> {
            if (projectComboBox.getValue() != null) {
                selectedProjectLabel.setText("Selected: " + projectComboBox.getValue());
            }
        });
    }

    private void setupTable() {
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        assignedColumn.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedHours"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        checklistTable.setItems(taskList);
    }

    private void setupComboBoxes() {
        projectComboBox.getItems().addAll("E-commerce Website", "Mobile Banking App", "Data Analytics Dashboard");
        priorityComboBox.getItems().addAll("High", "Medium", "Low");
        assignedToComboBox.getItems().addAll("John Smith", "Sarah Wilson", "Mike Johnson", "Lisa Chen", "David Brown");
    }

    private void addSampleData() {
        taskList.addAll(
                new Task(1, "Setup project structure", "High", "John Smith",
                        LocalDate.now().plusDays(3), 8f, true),
                new Task(2, "Create wireframes", "Medium", "Sarah Wilson",
                        LocalDate.now().plusDays(7), 16f, false),
                new Task(3, "Database design", "High", "Mike Johnson",
                        LocalDate.now().plusDays(5), 12f, true)
        );
        nextTaskId = 4;
    }

    @FXML
    void handleAddTask(ActionEvent event) {
        statusLabel.setText("");

        if (taskDescriptionTextField.getText().isEmpty() || priorityComboBox.getValue() == null ||
                assignedToComboBox.getValue() == null || dueDatePicker.getValue() == null ||
                estimatedHoursTextField.getText().isEmpty()) {
            statusLabel.setText("Please fill all required fields");
            return;
        }

        try {
            float hours = Float.parseFloat(estimatedHoursTextField.getText());

            Task newTask = new Task(
                    nextTaskId++,
                    taskDescriptionTextField.getText(),
                    priorityComboBox.getValue(),
                    assignedToComboBox.getValue(),
                    dueDatePicker.getValue(),
                    hours,
                    criticalTaskCheckBox.isSelected()
            );

            newTask.setNotes(taskNotesTextArea.getText());
            taskList.add(newTask);
            clearTaskForm();
            statusLabel.setText("Task added successfully!");

        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid hours format");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handleClearForm(ActionEvent event) {
        clearTaskForm();
    }

    @FXML
    void handleCreateChecklist(ActionEvent event) {
        if (projectComboBox.getValue() == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        if (taskList.isEmpty()) {
            statusLabel.setText("Please add at least one task");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checklist Created");
        alert.setHeaderText("Success");
        alert.setContentText("Checklist created for " + projectComboBox.getValue() +
                " with " + taskList.size() + " tasks");
        alert.showAndWait();
    }

    @FXML
    void handleDone(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handleEditTask(ActionEvent event) {
        Task selectedTask = checklistTable.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            statusLabel.setText("Please select a task to edit");
            return;
        }

        taskDescriptionTextField.setText(selectedTask.getDescription());
        priorityComboBox.setValue(selectedTask.getPriority());
        assignedToComboBox.setValue(selectedTask.getAssignedTo());
        dueDatePicker.setValue(selectedTask.getDueDate());
        estimatedHoursTextField.setText(String.valueOf(selectedTask.getEstimatedHours()));
        criticalTaskCheckBox.setSelected(selectedTask.isCritical());
        taskNotesTextArea.setText(selectedTask.getNotes());

        taskList.remove(selectedTask);
    }

    @FXML
    void handleRemoveTask(ActionEvent event) {
        Task selectedTask = checklistTable.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            statusLabel.setText("Please select a task to remove");
            return;
        }

        taskList.remove(selectedTask);
        statusLabel.setText("Task removed successfully");
    }

    private void clearTaskForm() {
        taskDescriptionTextField.clear();
        priorityComboBox.setValue(null);
        assignedToComboBox.setValue(null);
        dueDatePicker.setValue(null);
        estimatedHoursTextField.clear();
        criticalTaskCheckBox.setSelected(false);
        taskNotesTextArea.clear();
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}