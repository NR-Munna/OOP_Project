package com.example.oop_project_group19.Adnan;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class RRateCompleteProjectController {

    @FXML private Button backBtn;
    @FXML private TableColumn<CompletedProject, String> clientCol;
    @FXML private ComboBox<String> codeQualityRating;
    @FXML private ComboBox<String> communicationRating;
    @FXML private TableView<CompletedProject> completedProjectsTable;
    @FXML private TableColumn<CompletedProject, LocalDateTime> completionDateCol;
    @FXML private TableColumn<CompletedProject, String> freelancerCol;
    @FXML private ComboBox<String> overallRating;
    @FXML private TableColumn<CompletedProject, String> projectIdCol;
    @FXML private TableColumn<CompletedProject, String> projectNameCol;
    @FXML private ComboBox<String> requirementsRating;
    @FXML private TextArea reviewComments;
    @FXML private Button saveReviewBtn;
    @FXML private ComboBox<String> timelinessRating;

    private Scene scene;
    private ObservableList<CompletedProject> completedProjectsList = FXCollections.observableArrayList();

    public void initialize() {
        projectIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectId()));
        projectNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectName()));
        freelancerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFreelancer()));
        clientCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient()));
        completionDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCompletionDate()));

        ObservableList<String> ratingOptions = FXCollections.observableArrayList("1 - Poor", "2 - Fair", "3 - Good", "4 - Very Good", "5 - Excellent");

        codeQualityRating.setItems(ratingOptions);
        communicationRating.setItems(ratingOptions);
        overallRating.setItems(ratingOptions);
        requirementsRating.setItems(ratingOptions);
        timelinessRating.setItems(ratingOptions);

        loadSampleData();
        completedProjectsTable.setItems(completedProjectsList);

        completedProjectsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                reviewComments.setText("Project Details:\nProject: " + newSelection.getProjectName() +
                        "\nFreelancer: " + newSelection.getFreelancer() + "\nClient: " + newSelection.getClient() +
                        "\nCompleted: " + newSelection.getCompletionDate());
            }
        });
    }

    private void loadSampleData() {
        completedProjectsList.add(new CompletedProject("CP001", "Website Development", "Adnan Freelancer", "ABC Corp"));
        completedProjectsList.add(new CompletedProject("CP002", "Mobile App", "Samira Developer", "XYZ Ltd"));
        completedProjectsList.add(new CompletedProject("CP003", "Logo Design", "Naimur Designer", "Tech Start"));
        completedProjectsList.add(new CompletedProject("CP004", "Database Setup", "Shawon Coder", "Data Inc"));
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleSaveReview(ActionEvent event) {
        CompletedProject selected = completedProjectsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String codeQuality = codeQualityRating.getValue();
            String communication = communicationRating.getValue();
            String overall = overallRating.getValue();
            String requirements = requirementsRating.getValue();
            String timeliness = timelinessRating.getValue();
            String comments = reviewComments.getText();

            if (codeQuality != null && communication != null && overall != null && requirements != null && timeliness != null) {
                String reviewSummary = "\n\nREVIEW SAVED:\n" +
                        "Code Quality: " + codeQuality + "\n" +
                        "Communication: " + communication + "\n" +
                        "Requirements: " + requirements + "\n" +
                        "Timeliness: " + timeliness + "\n" +
                        "Overall: " + overall + "\n" +
                        "Comments: " + comments;

                reviewComments.appendText(reviewSummary);

                codeQualityRating.setValue(null);
                communicationRating.setValue(null);
                overallRating.setValue(null);
                requirementsRating.setValue(null);
                timelinessRating.setValue(null);
            }
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
}