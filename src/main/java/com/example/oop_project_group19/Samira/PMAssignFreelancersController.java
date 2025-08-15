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

public class PMAssignFreelancersController implements Initializable {

    @FXML private Button assignButton;
    @FXML private Button assignFreelancerButton;
    @FXML private TextField assignmentNotesTextField;
    @FXML private TableColumn<Freelancer, Boolean> availabilityColumn;
    @FXML private Button backButton;
    @FXML private TableColumn<Project, Float> budgetColumn;
    @FXML private TableColumn<Project, LocalDate> deadlineColumn;
    @FXML private Button filterButton;
    @FXML private TableColumn<Freelancer, Integer> freelancerIdColumn;
    @FXML private TableView<Freelancer> freelancersTable;
    @FXML private TableColumn<Freelancer, Float> hourlyRateColumn;
    @FXML private TableColumn<Freelancer, String> nameColumn;
    @FXML private TableColumn<Project, Integer> projectIdColumn;
    @FXML private TableColumn<Project, String> projectNameColumn;
    @FXML private TableView<Project> projectsTable;
    @FXML private TableColumn<Freelancer, Float> ratingColumn;
    @FXML private Label selectedFreelancerLabel;
    @FXML private Label selectedProjectLabel;
    @FXML private TextField skillFilterTextField;
    @FXML private TableColumn<Freelancer, String> skillsColumn;
    @FXML private TableColumn<Project, String> skillsRequiredColumn;
    @FXML private Label statusLabel;

    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private ObservableList<Freelancer> freelancerList = FXCollections.observableArrayList();
    private Project selectedProject;
    private Freelancer selectedFreelancer;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupProjectTable();
        setupFreelancerTable();
        addSampleData();

        projectsTable.setOnMouseClicked(event -> {
            selectedProject = projectsTable.getSelectionModel().getSelectedItem();
            if (selectedProject != null) {
                selectedProjectLabel.setText("Selected: " + selectedProject.getProjectName());
            }
        });

        freelancersTable.setOnMouseClicked(event -> {
            selectedFreelancer = freelancersTable.getSelectionModel().getSelectedItem();
            if (selectedFreelancer != null) {
                selectedFreelancerLabel.setText("Selected: " + selectedFreelancer.getName());
            }
        });
    }

    private void setupProjectTable() {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        projectsTable.setItems(projectList);
    }

    private void setupFreelancerTable() {
        freelancerIdColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
        hourlyRateColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        freelancersTable.setItems(freelancerList);
    }

    private void addSampleData() {
        projectList.addAll(
                new Project(1, "E-commerce Website", "TechCorp", "Planning",
                        LocalDate.now(), LocalDate.now().plusDays(60), 15000f),
                new Project(2, "Mobile Banking App", "StartupHub", "Planning",
                        LocalDate.now(), LocalDate.now().plusDays(90), 25000f)
        );

        freelancerList.addAll(
                new Freelancer(1, "Micheal Jackson", "Java, Spring Boot", 50f, 4.8f, true),
                new Freelancer(2, "Sarah Wilson", "React, Node.js", 45f, 4.9f, true),
                new Freelancer(3, "Mike Johnson", "Python, Django", 48f, 4.7f, false),
                new Freelancer(4, "Lisa Chen", "Mobile Development", 52f, 4.9f, true)
        );
    }

    @FXML
    void handleAssign(ActionEvent event) {
        if (selectedProject == null || selectedFreelancer == null) {
            statusLabel.setText("Please select both a project and a freelancer");
            return;
        }

        if (!selectedFreelancer.isAvailable()) {
            statusLabel.setText("Selected freelancer is not available");
            return;
        }

        selectedFreelancer.setAvailable(false);
        selectedProject.setStatus("Assigned");

        statusLabel.setText("Successfully assigned " + selectedFreelancer.getName() +
                " to " + selectedProject.getProjectName());

        freelancersTable.refresh();
        projectsTable.refresh();
    }

    @FXML
    void handleAssignFreelancer(ActionEvent event) {
        handleAssign(event);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ProjectManagerDashboard.fxml");
    }

    @FXML
    void handleFilter(ActionEvent event) {
        String filterText = skillFilterTextField.getText().toLowerCase();
        ObservableList<Freelancer> filteredList = FXCollections.observableArrayList();

        for (Freelancer freelancer : freelancerList) {
            if (freelancer.getSkills().toLowerCase().contains(filterText)) {
                filteredList.add(freelancer);
            }
        }

        freelancersTable.setItems(filteredList);
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