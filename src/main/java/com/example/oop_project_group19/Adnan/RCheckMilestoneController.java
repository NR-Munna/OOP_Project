package com.example.oop_project_group19.Adnan;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.time.LocalDateTime;

public class RCheckMilestoneController {

    @FXML private TableView<CompletedProject> activeProjectsTable;
    @FXML private Button approveBtn;
    @FXML private Button backBtn;
    @FXML private TableColumn<Milestone, LocalDateTime> deadlineCol;
    @FXML private TableColumn<Milestone, String> deliverableCol;
    @FXML private TableColumn<Milestone, LocalDateTime> dueDateCol;
    @FXML private TableColumn<CompletedProject, String> freelancerCol;
    @FXML private TextArea milestoneComments;
    @FXML private TableColumn<Milestone, String> milestoneNameCol;
    @FXML private TableView<Milestone> milestonesTable;
    @FXML private TableColumn<Milestone, String> notesCol;
    @FXML private TableColumn<Milestone, Float> progressCol;
    @FXML private TableColumn<CompletedProject, String> projectIdCol;
    @FXML private TableColumn<CompletedProject, String> projectNameCol;
    @FXML private Button requestRevisionBtn;
    @FXML private TableColumn<Milestone, LocalDateTime> startDateCol;
    @FXML private TableColumn<Milestone, String> statusCol;

    private Scene scene;
    private ObservableList<CompletedProject> activeProjectsList = FXCollections.observableArrayList();
    private ObservableList<Milestone> milestonesList = FXCollections.observableArrayList();

    public void initialize() {
        projectIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectId()));
        projectNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectName()));
        freelancerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFreelancer()));

        milestoneNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMilestoneName()));
        deliverableCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeliverable()));
        deadlineCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDeadline()));
        dueDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDueDate()));
        startDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
        progressCol.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getProgress()).asObject());
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        notesCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNotes()));

        loadSampleData();
        activeProjectsTable.setItems(activeProjectsList);
        milestonesTable.setItems(milestonesList);

        activeProjectsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadMilestonesForProject(newSelection.getProjectId());
            }
        });
    }

    private void loadSampleData() {
        activeProjectsList.add(new CompletedProject("AP001", "E-commerce Website", "IRAS Developer", "ABC Corp"));
        activeProjectsList.add(new CompletedProject("AP002", "Mobile Banking App", "IUB Coder", "Bank Ltd"));
        activeProjectsList.add(new CompletedProject("AP003", "Inventory System", "CSE Programmer", "Store Inc"));
    }

    private void loadMilestonesForProject(String projectId) {
        milestonesList.clear();
        milestonesList.add(new Milestone("M1", "Design Phase", "UI/UX Mockups", 100.0f, "Completed"));
        milestonesList.add(new Milestone("M2", "Development Phase", "Core Features", 75.0f, "In Progress"));
        milestonesList.add(new Milestone("M3", "Testing Phase", "Quality Assurance", 0.0f, "Pending"));
    }

    @FXML
    void handleApproveMilestone(ActionEvent event) {
        Milestone selected = milestonesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Approved");
            milestoneComments.setText("Milestone '" + selected.getMilestoneName() + "' has been approved.\n" +
                    "Deliverable: " + selected.getDeliverable() + "\n" +
                    "Progress: " + selected.getProgress() + "%");
            milestonesTable.refresh();
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleRequestRevision(ActionEvent event) {
        Milestone selected = milestonesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Revision Requested");
            milestoneComments.setText("Revision requested for milestone '" + selected.getMilestoneName() + "'.\n" +
                    "Please review the deliverable: " + selected.getDeliverable());
            milestonesTable.refresh();
        }
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

    private static class Milestone {
        private String milestoneId;
        private String milestoneName;
        private String deliverable;
        private float progress;
        private String status;
        private LocalDateTime deadline;
        private LocalDateTime dueDate;
        private LocalDateTime startDate;
        private String notes;

        public Milestone(String milestoneId, String milestoneName, String deliverable, float progress, String status) {
            this.milestoneId = milestoneId;
            this.milestoneName = milestoneName;
            this.deliverable = deliverable;
            this.progress = progress;
            this.status = status;
            this.deadline = LocalDateTime.now().plusDays(7);
            this.dueDate = LocalDateTime.now().plusDays(5);
            this.startDate = LocalDateTime.now().minusDays(10);
            this.notes = "Standard milestone";
        }

        public String getMilestoneId() { return milestoneId; }
        public String getMilestoneName() { return milestoneName; }
        public String getDeliverable() { return deliverable; }
        public float getProgress() { return progress; }
        public String getStatus() { return status; }
        public LocalDateTime getDeadline() { return deadline; }
        public LocalDateTime getDueDate() { return dueDate; }
        public LocalDateTime getStartDate() { return startDate; }
        public String getNotes() { return notes; }
        public void setStatus(String status) { this.status = status; }
    }
}