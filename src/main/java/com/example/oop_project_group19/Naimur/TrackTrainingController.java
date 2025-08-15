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

public class TrackTrainingController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button completeBtn;

    @FXML
    private TableColumn<Training, String> courseColumn;

    @FXML
    private TableColumn<Training, String> freelancerColumn;

    @FXML
    private TableColumn<Training, Float> progressColumn;

    @FXML
    private Label selectedCourseLabel;

    @FXML
    private Label selectedFreelancerLabel;

    @FXML
    private Label selectedProgressLabel;

    @FXML
    private TableColumn<Training, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Training, String> statusColumn;

    @FXML
    private CheckBox sufficientCheckBox;

    @FXML
    private TableView<Training> trainingTable;

    @FXML
    private Button updateBtn;

    private Scene scene;
    private ObservableList<Training> trainingRecords;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        loadTrainingRecords();
        setupTableSelectionListener();
        clearSelection();
    }

    private void initializeTable() {
        freelancerColumn.setCellValueFactory(new PropertyValueFactory<>("freelancer"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        progressColumn.setCellFactory(column -> new TableCell<Training, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.1f%%", item));
                }
            }
        });
    }

    private void loadTrainingRecords() {
        trainingRecords = FXCollections.observableArrayList(
                new Training("Christian Williams", "Advanced Java Programming", LocalDate.of(2024, 1, 10), 85.0f, "In Progress"),
                new Training("Jane Smith", "React Development", LocalDate.of(2024, 1, 15), 92.0f, "In Progress"),
                new Training("Mike Johnson", "Database Design", LocalDate.of(2024, 2, 1), 78.0f, "In Progress"),
                new Training("Sarah Wilson", "UI/UX Fundamentals", LocalDate.of(2024, 1, 20), 95.0f, "In Progress"),
                new Training("David Brown", "Node.js Essentials", LocalDate.of(2024, 2, 5), 68.0f, "In Progress"),
                new Training("Alex Turner", "Python for Data Science", LocalDate.of(2024, 1, 25), 100.0f, "Completed"),
                new Training("Emma Davis", "Mobile App Development", LocalDate.of(2024, 2, 10), 45.0f, "In Progress")
        );
        trainingTable.setItems(trainingRecords);
    }

    private void setupTableSelectionListener() {
        trainingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedFreelancerLabel.setText("Freelancer: " + newSelection.getFreelancer());
                selectedCourseLabel.setText("Course: " + newSelection.getCourse());
                selectedProgressLabel.setText(String.format("Progress: %.1f%%", newSelection.getProgress()));

                sufficientCheckBox.setSelected(newSelection.getProgress() >= 80.0f);

                updateBtn.setDisable(false);
                completeBtn.setDisable(false);
            } else {
                clearSelection();
            }
        });
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onCompleteTraining(ActionEvent event) {
        Training selectedTraining = trainingTable.getSelectionModel().getSelectedItem();
        if (selectedTraining == null) {
            showAlert("Please select a training record to complete", Alert.AlertType.WARNING);
            return;
        }

        if (selectedTraining.getStatus().equals("Completed")) {
            showAlert("This training is already completed", Alert.AlertType.INFORMATION);
            return;
        }

        if (!sufficientCheckBox.isSelected()) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Insufficient Progress");
            confirmAlert.setHeaderText("Training Progress Below Standard");
            confirmAlert.setContentText("The current progress is below 80%. Are you sure you want to mark this training as completed?");

            if (confirmAlert.showAndWait().get() != ButtonType.OK) {
                return;
            }
        }

        selectedTraining.setStatus("Completed");
        if (selectedTraining.getProgress() < 100.0f) {
            selectedTraining.setProgress(100.0f);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Training Completed");
        alert.setHeaderText("Success");
        alert.setContentText("Training '" + selectedTraining.getCourse() + "' for " + selectedTraining.getFreelancer() + " has been marked as completed.");
        alert.showAndWait();

        trainingTable.refresh();
        selectedProgressLabel.setText(String.format("Progress: %.1f%%", selectedTraining.getProgress()));
        updateBtn.setDisable(true);
        completeBtn.setDisable(true);
    }

    @FXML
    void onUpdateProgress(ActionEvent event) {
        Training selectedTraining = trainingTable.getSelectionModel().getSelectedItem();
        if (selectedTraining == null) {
            showAlert("Please select a training record to update", Alert.AlertType.WARNING);
            return;
        }

        if (selectedTraining.getStatus().equals("Completed")) {
            showAlert("Cannot update progress for completed training", Alert.AlertType.WARNING);
            return;
        }

        TextInputDialog dialog = new TextInputDialog(String.valueOf(selectedTraining.getProgress()));
        dialog.setTitle("Update Training Progress");
        dialog.setHeaderText("Update Progress for " + selectedTraining.getFreelancer());
        dialog.setContentText("Enter new progress percentage (0-100):");

        dialog.showAndWait().ifPresent(input -> {
            try {
                float newProgress = Float.parseFloat(input);
                if (newProgress < 0 || newProgress > 100) {
                    showAlert("Progress must be between 0 and 100", Alert.AlertType.ERROR);
                    return;
                }

                selectedTraining.setProgress(newProgress);
                sufficientCheckBox.setSelected(newProgress >= 80.0f);
                selectedProgressLabel.setText(String.format("Progress: %.1f%%", newProgress));

                trainingTable.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Progress Updated");
                alert.setHeaderText("Success");
                alert.setContentText("Progress updated to " + String.format("%.1f%%", newProgress));
                alert.showAndWait();

            } catch (NumberFormatException e) {
                showAlert("Please enter a valid number", Alert.AlertType.ERROR);
            }
        });
    }

    private void clearSelection() {
        selectedFreelancerLabel.setText("Freelancer: None selected");
        selectedCourseLabel.setText("Course: None selected");
        selectedProgressLabel.setText("Progress: None selected");
        sufficientCheckBox.setSelected(false);
        updateBtn.setDisable(true);
        completeBtn.setDisable(true);
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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