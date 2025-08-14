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

public class PMEditProjectUpdatesController implements Initializable {

    @FXML private TableColumn<Project, String> assignedToColumn;
    @FXML private Button backButton;
    @FXML private TableColumn<Project, String> currentStatusColumn;
    @FXML private TableColumn<Project, LocalDate> deadlineColumn;
    @FXML private TextArea notesTextArea;
    @FXML private CheckBox notifyClientCheckBox;
    @FXML private CheckBox notifyTeamCheckBox;
    @FXML private Button previewButton;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private TableColumn<Project, Integer> progressColumn;
    @FXML private Label progressLabel;
    @FXML private Slider progressSlider;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableColumn<Project, String> projectNameColumn;
    @FXML private TableView<Project> projectsTable;
    @FXML private ComboBox<String> qualityRatingComboBox;
    @FXML private Button resetButton;
    @FXML private Label selectedProjectLabel;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label statusLabel;
    @FXML private Button updateButton;
    @FXML private CheckBox updateTimestampCheckBox;

    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private Project selectedProject;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        setupComboBoxes();
        addSampleData();

        progressSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            progressLabel.setText(String.format("Progress: %.0f%%", newVal.doubleValue()));
        });

        projectsTable.setOnMouseClicked(event -> {
            selectedProject = projectsTable.getSelectionModel().getSelectedItem();
            if (selectedProject != null) {
                selectedProjectLabel.setText("Selected: " + selectedProject.getProjectName());
                statusComboBox.setValue(selectedProject.getStatus());
                progressSlider.setValue(50);
            }
        });
    }

    private void setupTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        currentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        assignedToColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        projectsTable.setItems(projectList);
    }

    private void setupComboBoxes() {
        statusComboBox.getItems().addAll("Planning", "In Progress", "Review", "Completed", "On Hold", "Cancelled");
        priorityComboBox.getItems().addAll("High", "Medium", "Low");
        qualityRatingComboBox.getItems().addAll("Excellent", "Good", "Average", "Below Average", "Poor");
    }

    private void addSampleData() {
        projectList.addAll(
                new Project(1, "E-commerce Website", "TechCorp", "In Progress",
                        LocalDate.now().minusDays(30), LocalDate.now().plusDays(5), 15000f),
                new Project(2, "Mobile Banking App", "StartupHub", "Review",
                        LocalDate.now().minusDays(20), LocalDate.now().plusDays(15), 25000f),
                new Project(3, "Data Analytics Dashboard", "DataSystems", "Planning",
                        LocalDate.now(), LocalDate.now().plusDays(45), 18000f)
        );
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handlePreview(ActionEvent event) {
        if (selectedProject == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        String preview = "Project: " + selectedProject.getProjectName() + "\n" +
                "New Status: " + statusComboBox.getValue() + "\n" +
                "Progress: " + String.format("%.0f%%", progressSlider.getValue()) + "\n" +
                "Priority: " + priorityComboBox.getValue() + "\n" +
                "Quality Rating: " + qualityRatingComboBox.getValue() + "\n" +
                "Notes: " + notesTextArea.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Preview");
        alert.setHeaderText("Project Update Preview");
        alert.setContentText(preview);
        alert.showAndWait();
    }

    @FXML
    void handleReset(ActionEvent event) {
        statusComboBox.setValue(null);
        priorityComboBox.setValue(null);
        qualityRatingComboBox.setValue(null);
        progressSlider.setValue(0);
        notesTextArea.clear();
        notifyClientCheckBox.setSelected(false);
        notifyTeamCheckBox.setSelected(false);
        updateTimestampCheckBox.setSelected(false);
        selectedProjectLabel.setText("No project selected");
        statusLabel.setText("");
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        if (selectedProject == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        if (statusComboBox.getValue() == null) {
            statusLabel.setText("Please select a status");
            return;
        }

        selectedProject.setStatus(statusComboBox.getValue());

        String notifications = "";
        if (notifyClientCheckBox.isSelected()) notifications += " Client notified.";
        if (notifyTeamCheckBox.isSelected()) notifications += " Team notified.";

        statusLabel.setText("Project updated successfully!" + notifications);
        projectsTable.refresh();
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