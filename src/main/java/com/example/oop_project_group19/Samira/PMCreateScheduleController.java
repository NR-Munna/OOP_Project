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

public class PMCreateScheduleController implements Initializable {

    @FXML private Button addToProjectButton;
    @FXML private Button backButton;
    @FXML private TableColumn<Project, Float> budgetColumn;
    @FXML private TextField budgetTextField;
    @FXML private Button clearFormButton;
    @FXML private ComboBox<String> clientComboBox;
    @FXML private TextArea descriptionTextArea;
    @FXML private TableColumn<Project, LocalDate> endDateColumn;
    @FXML private DatePicker endDatePicker;
    @FXML private Label errorLabel;
    @FXML private TableColumn<Project, Integer> idColumn;
    @FXML private TableColumn<Project, String> nameColumn;
    @FXML private TableColumn<Project, String> priorityColumn;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private TextField projectNameTextField;
    @FXML private ComboBox<String> projectTypeComboBox;
    @FXML private TableView<Project> projectsTable;
    @FXML private Button refreshButton;
    @FXML private TableColumn<Project, LocalDate> startDateColumn;
    @FXML private DatePicker startDatePicker;
    @FXML private TableColumn<Project, String> typeColumn;

    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private Scene scene;
    private int nextProjectId = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        clientComboBox.getItems().addAll("TechCorp", "StartupHub", "GlobalSoft", "DataSystems", "CloudTech");
        priorityComboBox.getItems().addAll("High", "Medium", "Low");
        projectTypeComboBox.getItems().addAll("Web Development", "Mobile App", "Data Analysis", "System Integration");

        projectsTable.setItems(projectList);
        addSampleData();
    }

    private void addSampleData() {
        projectList.addAll(
                new Project(1, "E-commerce Website", "TechCorp", "In Progress",
                        LocalDate.of(2024, 1, 15), LocalDate.of(2024, 3, 15), 15000f),
                new Project(2, "Mobile Banking App", "StartupHub", "Planning",
                        LocalDate.of(2024, 2, 1), LocalDate.of(2024, 5, 1), 25000f)
        );
        nextProjectId = 3;
    }

    @FXML
    void handleAddToProject(ActionEvent event) {
        errorLabel.setText("");

        if (projectNameTextField.getText().isEmpty() || clientComboBox.getValue() == null ||
                startDatePicker.getValue() == null || endDatePicker.getValue() == null ||
                budgetTextField.getText().isEmpty()) {
            errorLabel.setText("Please fill all required fields");
            return;
        }

        try {
            float budget = Float.parseFloat(budgetTextField.getText());
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (startDate.isAfter(endDate)) {
                errorLabel.setText("Start date cannot be after end date");
                return;
            }

            Project newProject = new Project(
                    nextProjectId++,
                    projectNameTextField.getText(),
                    clientComboBox.getValue(),
                    "Planning",
                    startDate,
                    endDate,
                    budget
            );

            projectList.add(newProject);
            handleClearForm(null);
            errorLabel.setText("Project added successfully!");

        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid budget format");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handleClearForm(ActionEvent event) {
        projectNameTextField.clear();
        clientComboBox.setValue(null);
        priorityComboBox.setValue(null);
        projectTypeComboBox.setValue(null);
        budgetTextField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        descriptionTextArea.clear();
        errorLabel.setText("");
    }

    @FXML
    void handleRefresh(ActionEvent event) {
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