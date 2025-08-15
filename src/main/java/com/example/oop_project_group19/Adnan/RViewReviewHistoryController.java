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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RViewReviewHistoryController {

    @FXML private Button backBtn;
    @FXML private TableColumn<ReviewHistory, LocalDateTime> dateCol;
    @FXML private Button filterBtn;
    @FXML private DatePicker fromDatePicker;
    @FXML private TableColumn<ReviewHistory, Float> ratingCol;
    @FXML private TextArea reviewDetailsArea;
    @FXML private TableView<ReviewHistory> reviewHistoryTable;
    @FXML private TableColumn<ReviewHistory, String> reviewIdCol;
    @FXML private ComboBox<String> reviewTypeCombo;
    @FXML private TableColumn<ReviewHistory, String> reviewerCol;
    @FXML private TableColumn<ReviewHistory, String> statusCol;
    @FXML private TableColumn<ReviewHistory, String> targetCol;
    @FXML private DatePicker toDatePicker;
    @FXML private TableColumn<ReviewHistory, String> typeCol;
    @FXML private Button viewDetailBtn;

    private Scene scene;
    private ObservableList<ReviewHistory> reviewHistoryList = FXCollections.observableArrayList();

    public void initialize() {
        reviewIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReviewId()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        targetCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTarget()));
        reviewerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReviewer()));
        dateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        ratingCol.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getRating()).asObject());
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        ObservableList<String> reviewTypes = FXCollections.observableArrayList("All", "Project Review", "Freelancer Review", "Milestone Review", "Quality Review");
        reviewTypeCombo.setItems(reviewTypes);

        loadSampleData();
        reviewHistoryTable.setItems(reviewHistoryList);

        reviewHistoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                reviewDetailsArea.setText("Review Details:\n" +
                        "Review ID: " + newSelection.getReviewId() + "\n" +
                        "Type: " + newSelection.getType() + "\n" +
                        "Target: " + newSelection.getTarget() + "\n" +
                        "Reviewer: " + newSelection.getReviewer() + "\n" +
                        "Date: " + newSelection.getDate() + "\n" +
                        "Rating: " + newSelection.getRating() + "\n" +
                        "Status: " + newSelection.getStatus() + "\n" +
                        "Comments: " + newSelection.getComments());
            }
        });
    }

    private void loadSampleData() {
        reviewHistoryList.add(new ReviewHistory("RH001", "Project Review", "Website Project", "Admin Reviewer", 4.5f, "Approved"));
        reviewHistoryList.add(new ReviewHistory("RH002", "Freelancer Review", "John Developer", "Lead Reviewer", 4.2f, "Approved"));
        reviewHistoryList.add(new ReviewHistory("RH003", "Quality Review", "Mobile App", "Quality Reviewer", 3.8f, "Revision Required"));
        reviewHistoryList.add(new ReviewHistory("RH004", "Milestone Review", "Database Setup", "Project Reviewer", 4.7f, "Completed"));
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleFilter(ActionEvent event) {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();
        String reviewType = reviewTypeCombo.getValue();

        reviewDetailsArea.setText("Filter Applied:\n" +
                "From Date: " + (fromDate != null ? fromDate.toString() : "Not set") + "\n" +
                "To Date: " + (toDate != null ? toDate.toString() : "Not set") + "\n" +
                "Review Type: " + (reviewType != null ? reviewType : "All") + "\n" +
                "Showing " + reviewHistoryList.size() + " reviews");
    }

    @FXML
    void handleViewDetail(ActionEvent event) {
        ReviewHistory selected = reviewHistoryTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            reviewDetailsArea.setText("DETAILED REVIEW:\n" +
                    "Review ID: " + selected.getReviewId() + "\n" +
                    "Type: " + selected.getType() + "\n" +
                    "Target: " + selected.getTarget() + "\n" +
                    "Reviewer: " + selected.getReviewer() + "\n" +
                    "Date: " + selected.getDate() + "\n" +
                    "Rating: " + selected.getRating() + "/5.0\n" +
                    "Status: " + selected.getStatus() + "\n" +
                    "Detailed Comments: " + selected.getComments());
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

    private static class ReviewHistory {
        private String reviewId;
        private String type;
        private String target;
        private String reviewer;
        private LocalDateTime date;
        private float rating;
        private String status;
        private String comments;

        public ReviewHistory(String reviewId, String type, String target, String reviewer, float rating, String status) {
            this.reviewId = reviewId;
            this.type = type;
            this.target = target;
            this.reviewer = reviewer;
            this.rating = rating;
            this.status = status;
            this.date = LocalDateTime.now().minusDays((int) (Math.random() * 30));
            this.comments = "Standard review comments for " + type;
        }

        public String getReviewId() { return reviewId; }
        public String getType() { return type; }
        public String getTarget() { return target; }
        public String getReviewer() { return reviewer; }
        public LocalDateTime getDate() { return date; }
        public float getRating() { return rating; }
        public String getStatus() { return status; }
        public String getComments() { return comments; }
    }
}