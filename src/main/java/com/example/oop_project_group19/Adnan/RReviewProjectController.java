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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class RReviewProjectController {

    @FXML private Button approveBtn;
    @FXML private Button backBtn;
    @FXML private TableColumn<ProjectPosting, String> clientNameCol;
    @FXML private TextArea commentsArea;
    @FXML private TableColumn<ProjectPosting, String> projectIdCol;
    @FXML private TableView<ProjectPosting> projectTable;
    @FXML private TableColumn<ProjectPosting, String> projectTitleCol;
    @FXML private Button rejectBtn;
    @FXML private TableColumn<ProjectPosting, String> statusCol;
    @FXML private TableColumn<ProjectPosting, LocalDateTime> submissionDateCol;

    private Scene scene;
    private ObservableList<ProjectPosting> projectList = FXCollections.observableArrayList();

    public void initialize() {
        projectIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectId()));
        projectTitleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectTitle()));
        clientNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClientName()));
        submissionDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSubmissionDate()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        loadSampleData();
        projectTable.setItems(projectList);

        projectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                commentsArea.setText("Project: " + newSelection.getProjectTitle() + "\nClient: " + newSelection.getClientName() +
                        "\nStatus: " + newSelection.getStatus() + "\nSubmitted: " + newSelection.getSubmissionDate());
            }
        });
    }

    private void loadSampleData() {
        projectList.add(new ProjectPosting("P001", "E-commerce Website", "Christian Williams"));
        projectList.add(new ProjectPosting("P002", "Mobile App Development", "Mirza Fakhrul"));
        projectList.add(new ProjectPosting("P003", "Data Analysis Project", "Narendra Modi"));
        projectList.add(new ProjectPosting("P004", "Logo Design", "Donald Trump"));
    }

    @FXML
    void handleApprove(ActionEvent event) {
        ProjectPosting selected = projectTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Approved");
            projectTable.refresh();
            String comment = commentsArea.getText();
            if (!comment.isEmpty()) {
                commentsArea.appendText("\n\nAPPROVED with comments: " + comment);
            }
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleReject(ActionEvent event) {
        ProjectPosting selected = projectTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Rejected");
            projectTable.refresh();
            String comment = commentsArea.getText();
            if (!comment.isEmpty()) {
                commentsArea.appendText("\n\nREJECTED with comments: " + comment);
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