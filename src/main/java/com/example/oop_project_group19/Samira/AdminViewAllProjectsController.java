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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminViewAllProjectsController implements Initializable {

    @FXML
    private Button applyFilterButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Project, Float> budgetColumn;

    @FXML
    private Button clearFilterButton;

    @FXML
    private TableColumn<Project, String> clientColumn;

    @FXML
    private TableColumn<Project, LocalDate> endDateColumn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField maxBudgetTextField;

    @FXML
    private TextField minBudgetTextField;

    @FXML
    private TableColumn<Project, Integer> projectIdColumn;

    @FXML
    private TableColumn<Project, String> projectNameColumn;

    @FXML
    private TableView<Project> projectsTable;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<Project, LocalDate> startDateColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<Project, String> statusColumn;

    @FXML
    private ComboBox<String> statusFilterComboBox;

    @FXML
    private Label totalBudgetLabel;

    @FXML
    private Label totalProjectsLabel;

    private Scene scene;
    private ObservableList<Project> allProjects;
    private ObservableList<Project> filteredProjects;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));

        statusFilterComboBox.setItems(FXCollections.observableArrayList(
                "All", "Active", "Completed", "On Hold", "Cancelled", "Pending"
        ));
        statusFilterComboBox.setValue("All");

        loadSampleData();
        filteredProjects = FXCollections.observableArrayList(allProjects);
        projectsTable.setItems(filteredProjects);
        updateLabels();
    }

    private void loadSampleData() {
        allProjects = FXCollections.observableArrayList(
                new Project(1, "E-commerce Website", "ABC Corp", "Active",
                        LocalDate.of(2024, 1, 10), LocalDate.of(2024, 6, 10), 15000.0f),
                new Project(2, "Mobile App Development", "XYZ Inc", "Completed",
                        LocalDate.of(2023, 11, 1), LocalDate.of(2024, 3, 1), 25000.0f),
                new Project(3, "Database Migration", "Tech Solutions", "On Hold",
                        LocalDate.of(2024, 2, 15), LocalDate.of(2024, 8, 15), 8000.0f),
                new Project(4, "UI/UX Redesign", "Creative Agency", "Active",
                        LocalDate.of(2024, 3, 1), LocalDate.of(2024, 7, 1), 12000.0f),
                new Project(5, "API Integration", "StartupCo", "Pending",
                        LocalDate.of(2024, 4, 1), LocalDate.of(2024, 9, 1), 18000.0f)
        );
    }

    @FXML
    void handleApplyFilter(ActionEvent event) {
        String statusFilter = statusFilterComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String minBudgetText = minBudgetTextField.getText().trim();
        String maxBudgetText = maxBudgetTextField.getText().trim();

        filteredProjects.clear();

        for (Project project : allProjects) {
            boolean matchesStatus = statusFilter.equals("All") || project.getStatus().equals(statusFilter);
            boolean matchesDateRange = true;
            boolean matchesBudgetRange = true;

            if (startDate != null && project.getStartDate().isBefore(startDate)) {
                matchesDateRange = false;
            }
            if (endDate != null && project.getEndDate().isAfter(endDate)) {
                matchesDateRange = false;
            }

            if (!minBudgetText.isEmpty()) {
                try {
                    float minBudget = Float.parseFloat(minBudgetText);
                    if (project.getBudget() < minBudget) {
                        matchesBudgetRange = false;
                    }
                } catch (NumberFormatException e) {
                    matchesBudgetRange = false;
                }
            }

            if (!maxBudgetText.isEmpty()) {
                try {
                    float maxBudget = Float.parseFloat(maxBudgetText);
                    if (project.getBudget() > maxBudget) {
                        matchesBudgetRange = false;
                    }
                } catch (NumberFormatException e) {
                    matchesBudgetRange = false;
                }
            }

            if (matchesStatus && matchesDateRange && matchesBudgetRange) {
                filteredProjects.add(project);
            }
        }

        updateLabels();
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleClearFilter(ActionEvent event) {
        statusFilterComboBox.setValue("All");
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        minBudgetTextField.clear();
        maxBudgetTextField.clear();

        filteredProjects.clear();
        filteredProjects.addAll(allProjects);
        updateLabels();
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        projectsTable.refresh();
        updateLabels();
    }

    private void updateLabels() {
        totalProjectsLabel.setText("Total Projects: " + filteredProjects.size());

        float totalBudget = 0;
        for (Project project : filteredProjects) {
            totalBudget += project.getBudget();
        }
        totalBudgetLabel.setText("Total Budget: $" + String.format("%.2f", totalBudget));
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