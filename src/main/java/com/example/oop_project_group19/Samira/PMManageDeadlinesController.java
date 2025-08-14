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
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class PMManageDeadlinesController implements Initializable {

    @FXML private TableColumn<Project, String> assignedToColumn;
    @FXML private Button backButton;
    @FXML private Button cancelButton;
    @FXML private TableColumn<Project, LocalDate> currentDeadlineColumn;
    @FXML private TableColumn<Project, Long> daysLeftColumn;
    @FXML private TableView<Project> deadlinesTable;
    @FXML private ComboBox<String> filterComboBox;
    @FXML private DatePicker newDeadlineDatePicker;
    @FXML private TextArea notesTextArea;
    @FXML private CheckBox notifyTeamCheckBox;
    @FXML private TableColumn<Project, String> priorityColumn;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableColumn<Project, String> projectNameColumn;
    @FXML private TextField reasonTextField;
    @FXML private Button refreshButton;
    @FXML private Label selectedProjectLabel;
    @FXML private TableColumn<Project, String> statusColumn;
    @FXML private Label statusLabel;
    @FXML private Button updateDeadlineButton;

    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private Project selectedProject;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        addSampleData();

        filterComboBox.getItems().addAll("All Projects", "Urgent (< 7 days)", "Soon (< 30 days)", "Later");

        deadlinesTable.setOnMouseClicked(event -> {
            selectedProject = deadlinesTable.getSelectionModel().getSelectedItem();
            if (selectedProject != null) {
                selectedProjectLabel.setText("Selected: " + selectedProject.getProjectName());
                newDeadlineDatePicker.setValue(selectedProject.getEndDate());
            }
        });
    }

    private void setupTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        currentDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        assignedToColumn.setCellValueFactory(new PropertyValueFactory<>("client"));

        daysLeftColumn.setCellValueFactory(cellData -> {
            LocalDate deadline = cellData.getValue().getEndDate();
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
            return new javafx.beans.property.SimpleLongProperty(daysLeft).asObject();
        });

        deadlinesTable.setItems(projectList);
    }

    private void addSampleData() {
        projectList.addAll(
                new Project(1, "E-commerce Website", "TechCorp", "In Progress",
                        LocalDate.now().minusDays(30), LocalDate.now().plusDays(5), 15000f),
                new Project(2, "Mobile Banking App", "StartupHub", "In Progress",
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
    void handleCancel(ActionEvent event) {
        newDeadlineDatePicker.setValue(null);
        reasonTextField.clear();
        notesTextArea.clear();
        notifyTeamCheckBox.setSelected(false);
        selectedProjectLabel.setText("No project selected");
        statusLabel.setText("");
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        deadlinesTable.refresh();
    }

    @FXML
    void handleUpdateDeadline(ActionEvent event) {
        if (selectedProject == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        if (newDeadlineDatePicker.getValue() == null) {
            statusLabel.setText("Please select a new deadline");
            return;
        }

        if (reasonTextField.getText().isEmpty()) {
            statusLabel.setText("Please provide a reason for deadline change");
            return;
        }

        LocalDate newDeadline = newDeadlineDatePicker.getValue();
        selectedProject.setEndDate(newDeadline);

        String notification = notifyTeamCheckBox.isSelected() ?
                " (Team notified)" : " (Team not notified)";

        statusLabel.setText("Deadline updated for " + selectedProject.getProjectName() + notification);
        deadlinesTable.refresh();
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