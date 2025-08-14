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
import java.time.LocalDateTime;

public class ReviewWorkController {

    @FXML
    private TableColumn<WorkFile, String> actionsColumn;

    @FXML
    private RadioButton approveRadio;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<WorkFile, String> fileNameColumn;

    @FXML
    private TableColumn<WorkFile, Double> fileSizeColumn;

    @FXML
    private TableColumn<WorkFile, String> fileTypeColumn;

    @FXML
    private TableView<WorkFile> filesTable;

    @FXML
    private TextArea freelancerCommentsArea;

    @FXML
    private ComboBox<Project> projectComboBox;

    @FXML
    private Button refreshButton;

    @FXML
    private RadioButton rejectRadio;

    @FXML
    private RadioButton requestChangesRadio;

    @FXML
    private TextArea reviewCommentsArea;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitReviewButton;

    @FXML
    private TableColumn<WorkFile, LocalDateTime> uploadDateColumn;

    private Scene scene;
    private ObservableList<WorkFile> filesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        projectComboBox.getItems().addAll(
                new Project("P1", "Website Development", "C1", "F1", "John Smith", null, null, 1500.0, "In Review"),
                new Project("P2", "Mobile App", "C1", "F2", "Sarah Johnson", null, null, 2000.0, "In Review")
        );

        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        fileTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fileType"));
        fileSizeColumn.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
        uploadDateColumn.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));

        filesTable.setItems(filesList);

        freelancerCommentsArea.setEditable(false);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleProjectSelection(ActionEvent event) {
        Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            loadWorkFiles(selectedProject);
            freelancerCommentsArea.setText("Project: " + selectedProject.getTitle() +
                    "\nFreelancer: " + selectedProject.getFreelancerName() +
                    "\n\nFreelancer Comments:\n'Project completed as per requirements. All deliverables included. " +
                    "Please review the files and let me know if any changes are needed.'");
            statusLabel.setText("Submitted work content displayed");
        }
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            loadWorkFiles(selectedProject);
            statusLabel.setText("Work content refreshed");
        } else {
            statusLabel.setText("Please select a project first");
        }
    }

    @FXML
    void handleSubmitReview(ActionEvent event) {
        if (projectComboBox.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select a project");
            return;
        }

        if (statusGroup.getSelectedToggle() == null) {
            statusLabel.setText("Please select review status (Approve/Reject/Request Changes)");
            return;
        }

        String reviewText = reviewCommentsArea.getText().trim();
        if (reviewText.isEmpty()) {
            statusLabel.setText("Please provide review comments");
            return;
        }

        RadioButton selected = (RadioButton) statusGroup.getSelectedToggle();
        statusLabel.setText("Work review submitted: " + selected.getText());
        reviewCommentsArea.clear();
        statusGroup.selectToggle(null);
    }

    private void loadWorkFiles(Project project) {
        filesList.clear();
        filesList.addAll(
                new WorkFile("website_homepage.html", "HTML", 25.6),
                new WorkFile("styles.css", "CSS", 12.3),
                new WorkFile("script.js", "JavaScript", 8.9),
                new WorkFile("documentation.pdf", "PDF", 156.7)
        );
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