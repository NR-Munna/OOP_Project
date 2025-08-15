package com.example.oop_project_group19.Naimur;

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

public class ViewRatingsController implements Initializable {

    @FXML
    private Label averageRatingLabel;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Rating, String> clientColumn;

    @FXML
    private TableColumn<Rating, String> commentColumn;

    @FXML
    private TableColumn<Rating, LocalDate> dateColumn;

    @FXML
    private Button filterBtn;

    @FXML
    private TableColumn<Rating, String> freelancerColumn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableColumn<Rating, String> projectColumn;

    @FXML
    private TableColumn<Rating, Float> ratingColumn;

    @FXML
    private ComboBox<String> ratingFilterCombo;

    @FXML
    private TableView<Rating> ratingsTable;

    @FXML
    private Button refreshBtn;

    @FXML
    private DatePicker toDatePicker;

    private Scene scene;
    private ObservableList<Rating> allRatings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeComboBox();
        loadRatings();
    }

    private void initializeTable() {
        freelancerColumn.setCellValueFactory(new PropertyValueFactory<>("freelancer"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("project"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void initializeComboBox() {
        ObservableList<String> ratingFilters = FXCollections.observableArrayList(
                "All Ratings", "5 Stars", "4 Stars", "3 Stars", "2 Stars", "1 Star"
        );
        ratingFilterCombo.setItems(ratingFilters);
        ratingFilterCombo.setValue("All Ratings");
    }

    private void loadRatings() {
        allRatings = FXCollections.observableArrayList(
                new Rating("Christian Williams", "ABC Corp", "Website Design", 4.5f, "Great work!", LocalDate.of(2024, 1, 15)),
                new Rating("Jane Smith", "XYZ Ltd", "Mobile App", 5.0f, "Excellent developer", LocalDate.of(2024, 1, 20)),
                new Rating("Mike Johnson", "Tech Solutions", "Database Design", 4.0f, "Good job", LocalDate.of(2024, 1, 25)),
                new Rating("Sarah Wilson", "StartUp Inc", "UI/UX Design", 4.8f, "Amazing designer", LocalDate.of(2024, 2, 5)),
                new Rating("David Brown", "Global Tech", "Backend API", 4.2f, "Solid work", LocalDate.of(2024, 2, 10))
        );
        ratingsTable.setItems(allRatings);
        updateAverageRating();
    }

    private void updateAverageRating() {
        if (allRatings.isEmpty()) {
            averageRatingLabel.setText("Average Rating: N/A");
            return;
        }

        float total = 0;
        for (Rating rating : allRatings) {
            total += rating.getRating();
        }
        float average = total / allRatings.size();
        averageRatingLabel.setText(String.format("Average Rating: %.2f", average));
    }

    @FXML
    void onApplyFilter(ActionEvent event) {
        ObservableList<Rating> filteredRatings = FXCollections.observableArrayList();

        for (Rating rating : allRatings) {
            boolean dateMatch = true;
            boolean ratingMatch = true;

            if (fromDatePicker.getValue() != null && rating.getDate().isBefore(fromDatePicker.getValue())) {
                dateMatch = false;
            }

            if (toDatePicker.getValue() != null && rating.getDate().isAfter(toDatePicker.getValue())) {
                dateMatch = false;
            }

            String selectedRating = ratingFilterCombo.getValue();
            if (!selectedRating.equals("All Ratings")) {
                int starCount = Integer.parseInt(selectedRating.substring(0, 1));
                if (Math.round(rating.getRating()) != starCount) {
                    ratingMatch = false;
                }
            }

            if (dateMatch && ratingMatch) {
                filteredRatings.add(rating);
            }
        }

        ratingsTable.setItems(filteredRatings);
        updateFilteredAverageRating(filteredRatings);
    }

    private void updateFilteredAverageRating(ObservableList<Rating> ratings) {
        if (ratings.isEmpty()) {
            averageRatingLabel.setText("Average Rating: N/A");
            return;
        }

        float total = 0;
        for (Rating rating : ratings) {
            total += rating.getRating();
        }
        float average = total / ratings.size();
        averageRatingLabel.setText(String.format("Average Rating: %.2f", average));
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onRefresh(ActionEvent event) {
        ratingsTable.setItems(allRatings);
        updateAverageRating();
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        ratingFilterCombo.setValue("All Ratings");
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