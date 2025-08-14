package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class ViewHistoryController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Project, Double> budgetColumn;

    @FXML
    private TextField completedProjectsField;

    @FXML
    private TableColumn<Project, LocalDate> endDateColumn;

    @FXML
    private TableColumn<Project, String> freelancerColumn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableView<Project> projectHistoryTable;

    @FXML
    private TextArea projectSummaryArea;

    @FXML
    private TableColumn<Project, String> projectTitleColumn;

    @FXML
    private TableColumn<Project, Double> ratingColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<Project, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Project, String> statusColumn;

    @FXML
    private ComboBox<String> statusFilterCombo;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TextField totalProjectsField;

    @FXML
    private TextField totalSpentField;

    @FXML
    private Button viewDetailsButton;

    private Scene scene;
    private ObservableList<Project> allProjects = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        statusFilterCombo.getItems().addAll(
                "All", "Completed", "In Progress", "Cancelled", "On Hold"
        );
        statusFilterCombo.setValue("All");

        projectTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        freelancerColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        projectHistoryTable.setItems(allProjects);

        totalProjectsField.setEditable(false);
        completedProjectsField.setEditable(false);
        totalSpentField.setEditable(false);

        loadProjectHistory();
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleSearch(ActionEvent event) {
        if (fromDatePicker.getValue() != null && toDatePicker.getValue() != null) {
            ObservableList<Project> filteredProjects = FXCollections.observableArrayList();

            for (Project project : allProjects) {
                if (project.getStartDate() != null &&
                        !project.getStartDate().isBefore(fromDatePicker.getValue()) &&
                        !project.getStartDate().isAfter(toDatePicker.getValue())) {
                    filteredProjects.add(project);
                }
            }

            projectHistoryTable.setItems(filteredProjects);
            projectSummaryArea.setText("Search Results:\nProjects from " + fromDatePicker.getValue() +
                    " to " + toDatePicker.getValue() + "\nFound " + filteredProjects.size() + " projects");
        } else {
            projectSummaryArea.setText("Please select date range for search");
        }
    }

    @FXML
    void handleStatusFilter(ActionEvent event) {
        String selectedStatus = statusFilterCombo.getSelectionModel().getSelectedItem();
        if (selectedStatus != null && !selectedStatus.equals("All")) {
            ObservableList<Project> filteredProjects = FXCollections.observableArrayList();

            for (Project project : allProjects) {
                if (project.getStatus().equals(selectedStatus)) {
                    filteredProjects.add(project);
                }
            }

            projectHistoryTable.setItems(filteredProjects);
            projectSummaryArea.setText("Filtered by status: " + selectedStatus +
                    "\nShowing " + filteredProjects.size() + " projects");
        } else {
            projectHistoryTable.setItems(allProjects);
            projectSummaryArea.setText("Showing all projects");
        }
    }

    @FXML
    void handleViewDetails(ActionEvent event) {
        Project selectedProject = projectHistoryTable.getSelectionModel().getSelectedItem();
        if (selectedProject == null) {
            projectSummaryArea.setText("Please select a project to view details");
            return;
        }

        projectSummaryArea.setText("Project Details:\n" +
                "Title: " + selectedProject.getTitle() + "\n" +
                "Freelancer: " + selectedProject.getFreelancerName() + "\n" +
                "Start Date: " + selectedProject.getStartDate() + "\n" +
                "End Date: " + selectedProject.getEndDate() + "\n" +
                "Budget: $" + selectedProject.getBudget() + "\n" +
                "Status: " + selectedProject.getStatus() + "\n" +
                "Rating: " + selectedProject.getRating() + " Stars\n" +
                "Description: Complete project with all deliverables met on time.");
    }

    private void loadProjectHistory() {
        allProjects.clear();
        allProjects.addAll(
                new Project("P1", "Website Development", "C1", "F1", "John Smith",
                        LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 30), 1500.0, "Completed"),
                new Project("P2", "Mobile App Development", "C1", "F2", "Sarah Johnson",
                        LocalDate.of(2024, 2, 1), LocalDate.of(2024, 3, 15), 2500.0, "Completed"),
                new Project("P3", "Logo Design", "C1", "F3", "Mike Brown",
                        LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 10), 500.0, "Completed"),
                new Project("P4", "E-commerce Site", "C1", "F1", "John Smith",
                        LocalDate.of(2024, 4, 1), null, 3000.0, "In Progress"),
                new Project("P5", "Content Writing", "C1", "F4", "Lisa Wilson",
                        LocalDate.of(2024, 3, 15), LocalDate.of(2024, 3, 25), 800.0, "Completed")
        );

        // Set ratings for completed projects
        allProjects.get(0).setRating(5.0);
        allProjects.get(1).setRating(4.5);
        allProjects.get(2).setRating(4.8);
        allProjects.get(4).setRating(4.2);

        updateSummaryFields();

        projectSummaryArea.setText("Project History Loaded:\n" +
                "All past projects fetched from system\n" +
                "Total Projects: " + allProjects.size() + "\n" +
                "Completed: " + getCompletedCount() + "\n" +
                "In Progress: " + getInProgressCount() + "\n" +
                "Average Rating: " + getAverageRating() + "/5\n\n" +
                "Project summaries displayed in table above");
    }

    private void updateSummaryFields() {
        totalProjectsField.setText(String.valueOf(allProjects.size()));
        completedProjectsField.setText(String.valueOf(getCompletedCount()));
        totalSpentField.setText("$" + String.format("%.2f", getTotalSpent()));
    }

    private long getCompletedCount() {
        return allProjects.stream().filter(p -> "Completed".equals(p.getStatus())).count();
    }

    private long getInProgressCount() {
        return allProjects.stream().filter(p -> "In Progress".equals(p.getStatus())).count();
    }

    private double getTotalSpent() {
        return allProjects.stream()
                .filter(p -> "Completed".equals(p.getStatus()))
                .mapToDouble(Project::getBudget)
                .sum();
    }

    private double getAverageRating() {
        return allProjects.stream()
                .filter(p -> "Completed".equals(p.getStatus()) && p.getRating() > 0)
                .mapToDouble(Project::getRating)
                .average()
                .orElse(0.0);
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