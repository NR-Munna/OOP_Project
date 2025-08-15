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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TReviewsComplaintsController {

    @FXML private RadioButton allReviewsRadio;
    @FXML private Button applyFilterBtn;
    @FXML private Button backBtn;
    @FXML private RadioButton complaintsRadio;
    @FXML private RadioButton negativeReviewsRadio;
    @FXML private RadioButton positiveReviewsRadio;
    @FXML private TableColumn<Review, String> priorityColumn;
    @FXML private TableColumn<Review, Float> ratingColumn;
    @FXML private TextArea responseNotesArea;
    @FXML private ComboBox<String> responseStatusCombo;
    @FXML private TableColumn<Review, String> reviewIdColumn;
    @FXML private ToggleGroup reviewTypeGroup;
    @FXML private TableView<Review> reviewsTable;
    @FXML private Button saveNotesBtn;
    @FXML private TextArea selectedContentArea;
    @FXML private TextField selectedUserInfoField;
    @FXML private TableColumn<Review, String> statusColumn;
    @FXML private TableColumn<Review, LocalDateTime> timestampColumn;
    @FXML private TableColumn<Review, String> typeColumn;
    @FXML private Button updateStatusBtn;
    @FXML private TableColumn<Review, String> userIdColumn;
    @FXML private TableColumn<Review, String> usernameColumn;

    private Scene scene;
    private ObservableList<Review> reviewList = FXCollections.observableArrayList();
    private ObservableList<Review> allReviews = FXCollections.observableArrayList();

    public void initialize() {
        reviewIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReviewId()));
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        ratingColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getRating()).asObject());
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        priorityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
        timestampColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTimestamp()));

        responseStatusCombo.setItems(FXCollections.observableArrayList("New", "In Review", "Responded", "Resolved"));

        allReviewsRadio.setSelected(true);

        loadSampleData();
        allReviews.addAll(reviewList);
        reviewsTable.setItems(reviewList);

        reviewsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedUserInfoField.setText(newSelection.getUsername() + " (" + newSelection.getUserId() + ")");
                selectedContentArea.setText(newSelection.getContent());
                responseStatusCombo.setValue(newSelection.getStatus());
            }
        });
    }

    private void loadSampleData() {
        reviewList.add(new Review("R001", "U001", "Christian Williams", "Review", 4.5f, "Great freelancer, delivered on time!", "Medium"));
        reviewList.add(new Review("R002", "U002", "Jane Smith", "Complaint", 2.0f, "Poor communication and late delivery", "High"));
        reviewList.add(new Review("R003", "U003", "Bob Johnson", "Review", 5.0f, "Excellent work quality!", "Low"));
        reviewList.add(new Review("R004", "U004", "Alice Brown", "Complaint", 1.5f, "Did not meet requirements", "High"));
    }

    @FXML
    void handleApplyFilter(ActionEvent event) {
        ObservableList<Review> filteredList = FXCollections.observableArrayList();

        if (allReviewsRadio.isSelected()) {
            filteredList.addAll(allReviews);
        } else if (positiveReviewsRadio.isSelected()) {
            for (Review review : allReviews) {
                if (review.getType().equals("Review") && review.getRating() >= 4.0f) {
                    filteredList.add(review);
                }
            }
        } else if (negativeReviewsRadio.isSelected()) {
            for (Review review : allReviews) {
                if (review.getType().equals("Review") && review.getRating() < 3.0f) {
                    filteredList.add(review);
                }
            }
        } else if (complaintsRadio.isSelected()) {
            for (Review review : allReviews) {
                if (review.getType().equals("Complaint")) {
                    filteredList.add(review);
                }
            }
        }

        reviewsTable.setItems(filteredList);
    }

    @FXML
    void handleSaveNotes(ActionEvent event) {
        String notes = responseNotesArea.getText();
        if (!notes.isEmpty()) {
            System.out.println("Notes saved: " + notes);
            responseNotesArea.clear();
        }
    }

    @FXML
    void handleUpdateStatus(ActionEvent event) {
        Review selected = reviewsTable.getSelectionModel().getSelectedItem();
        String newStatus = responseStatusCombo.getValue();

        if (selected != null && newStatus != null) {
            selected.setStatus(newStatus);
            reviewsTable.refresh();
            System.out.println("Status updated to: " + newStatus);
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
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