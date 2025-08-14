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

public class PMEditProjectDetailsController implements Initializable {

    @FXML private Button backButton;
    @FXML private TableColumn<Project, Float> budgetColumn;
    @FXML private TextField budgetTextField;
    @FXML private TableColumn<Project, String> clientColumn;
    @FXML private ComboBox<String> clientComboBox;
    @FXML private TableColumn<Project, LocalDate> deadlineColumn;
    @FXML private DatePicker deadlineDatePicker;
    @FXML private Button deleteButton;
    @FXML private TextArea descriptionTextArea;
    @FXML private Button editButton;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableView<Project> projectsTable;
    @FXML private Button resetButton;
    @FXML private Button saveButton;
    @FXML private TextField skillsTextField;
    @FXML private TextArea specificationsTextArea;
    @FXML private TableColumn<Project, String> statusColumn;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label statusLabel;
    @FXML private TextField teamSizeTextField;
    @FXML private TableColumn<Project, String> titleColumn;
    @FXML private TextField titleTextField;
    @FXML private CheckBox urgentCheckBox;

    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private Project selectedProject;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        setupComboBoxes();
        addSampleData();

        projectsTable.setOnMouseClicked(event -> {
            selectedProject = projectsTable.getSelectionModel().getSelectedItem();
            if (selectedProject != null) {
                populateFields();
            }
        });
    }

    private void setupTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        projectsTable.setItems(projectList);
    }

    private void setupComboBoxes() {
        clientComboBox.getItems().addAll("TechCorp", "StartupHub", "GlobalSoft", "DataSystems", "CloudTech");
        statusComboBox.getItems().addAll("Planning", "In Progress", "Review", "Completed", "On Hold", "Cancelled");
        priorityComboBox.getItems().addAll("High", "Medium", "Low");
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
    void handleDelete(ActionEvent event) {
        if (selectedProject == null) {
            statusLabel.setText("Please select a project to delete");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Project");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete " + selectedProject.getProjectName() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            projectList.remove(selectedProject);
            clearFields();
            statusLabel.setText("Project deleted successfully");
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        if (selectedProject == null) {
            statusLabel.setText("Please select a project to edit");
            return;
        }

        populateFields();
        statusLabel.setText("Edit mode enabled for " + selectedProject.getProjectName());
    }

    @FXML
    void handleReset(ActionEvent event) {
        if (selectedProject != null) {
            populateFields();
        } else {
            clearFields();
        }
        statusLabel.setText("Form reset");
    }

    @FXML
    void handleSave(ActionEvent event) {
        statusLabel.setText("");

        if (selectedProject == null) {
            statusLabel.setText("Please select a project to save");
            return;
        }

        if (titleTextField.getText().isEmpty() || clientComboBox.getValue() == null ||
                statusComboBox.getValue() == null || budgetTextField.getText().isEmpty() ||
                deadlineDatePicker.getValue() == null) {
            statusLabel.setText("Please fill all required fields");
            return;
        }

        try {
            float budget = Float.parseFloat(budgetTextField.getText());

            selectedProject.setProjectName(titleTextField.getText());
            selectedProject.setClient(clientComboBox.getValue());
            selectedProject.setStatus(statusComboBox.getValue());
            selectedProject.setBudget(budget);
            selectedProject.setEndDate(deadlineDatePicker.getValue());

            projectsTable.refresh();
            statusLabel.setText("Project details updated successfully");

        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid budget format");
        }
    }

    private void populateFields() {
        if (selectedProject != null) {
            titleTextField.setText(selectedProject.getProjectName());
            clientComboBox.setValue(selectedProject.getClient());
            statusComboBox.setValue(selectedProject.getStatus());
            budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
            deadlineDatePicker.setValue(selectedProject.getEndDate());

            descriptionTextArea.setText("Project: " + selectedProject.getProjectName());
            specificationsTextArea.setText("Specifications for " + selectedProject.getProjectName());
            skillsTextField.setText("Required skills for the project");
            teamSizeTextField.setText("5");
        }
    }

    private void clearFields() {
        titleTextField.clear();
        clientComboBox.setValue(null);
        statusComboBox.setValue(null);
        priorityComboBox.setValue(null);
        budgetTextField.clear();
        deadlineDatePicker.setValue(null);
        descriptionTextArea.clear();
        specificationsTextArea.clear();
        skillsTextField.clear();
        teamSizeTextField.clear();
        urgentCheckBox.setSelected(false);
        statusLabel.setText("");
        selectedProject = null;
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