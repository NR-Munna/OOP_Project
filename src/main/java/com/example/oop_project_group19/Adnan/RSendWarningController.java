package com.example.oop_project_group19.Adnan;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.time.LocalDateTime;

public class RSendWarningController {

    @FXML private Button backBtn;
    @FXML private TableColumn<FreelancerProfile, Float> currentRatingCol;
    @FXML private TableColumn<FreelancerProfile, String> emailCol;
    @FXML private TableColumn<FreelancerProfile, String> freelancerIdCol;
    @FXML private TableColumn<FreelancerProfile, LocalDateTime> lastWarningCol;
    @FXML private TableView<FreelancerProfile> lowRatedFreelancersTable;
    @FXML private ComboBox<String> messageTypeCombo;
    @FXML private TableColumn<FreelancerProfile, String> nameCol;
    @FXML private TableColumn<FreelancerProfile, Integer> projectsCompletedCol;
    @FXML private ComboBox<String> ratingThresholdCombo;
    @FXML private Button sendWarningBtn;
    @FXML private Button sortBtn;
    @FXML private TextArea warningMessageArea;

    private Scene scene;
    private ObservableList<FreelancerProfile> lowRatedFreelancersList = FXCollections.observableArrayList();

    public void initialize() {
        freelancerIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFreelancerId()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        currentRatingCol.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getRating()).asObject());
        lastWarningCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastWarning()));
        projectsCompletedCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(5).asObject());

        ObservableList<String> thresholdOptions = FXCollections.observableArrayList("2.0", "2.5", "3.0", "3.5", "4.0");
        ratingThresholdCombo.setItems(thresholdOptions);

        ObservableList<String> messageTypes = FXCollections.observableArrayList("Warning", "Final Warning", "Performance Notice", "Improvement Required");
        messageTypeCombo.setItems(messageTypes);

        loadSampleData();
        lowRatedFreelancersTable.setItems(lowRatedFreelancersList);

        lowRatedFreelancersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                warningMessageArea.setText("Dear " + newSelection.getName() + ",\n\n" +
                        "We have noticed that your current rating is " + newSelection.getRating() +
                        " which is below our quality standards.\n\n" +
                        "Please improve your work quality and client communication to maintain your account status.\n\n" +
                        "Best regards,\nReview Team");
            }
        });
    }

    private void loadSampleData() {
        lowRatedFreelancersList.add(new FreelancerProfile("FL005", "Poor Developer", "Basic HTML, CSS", 2.1f, "1 year", "Available", "poor@email.com"));
        lowRatedFreelancersList.add(new FreelancerProfile("FL006", "Average Coder", "JavaScript, PHP", 2.8f, "2 years", "Busy", "average@email.com"));
        lowRatedFreelancersList.add(new FreelancerProfile("FL007", "Slow Worker", "Python, Django", 2.5f, "3 years", "Available", "slow@email.com"));
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleSendWarning(ActionEvent event) {
        FreelancerProfile selected = lowRatedFreelancersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String messageType = messageTypeCombo.getValue();
            String warningMessage = warningMessageArea.getText();

            selected.setLastWarning(LocalDateTime.now());

            warningMessageArea.appendText("\n\n--- WARNING SENT ---\n" +
                    "To: " + selected.getName() + " (" + selected.getEmail() + ")\n" +
                    "Type: " + (messageType != null ? messageType : "Standard Warning") + "\n" +
                    "Date: " + LocalDateTime.now() + "\n" +
                    "Status: SENT");

            lowRatedFreelancersTable.refresh();
        }
    }

    @FXML
    void handleSort(ActionEvent event) {
        String threshold = ratingThresholdCombo.getValue();
        if (threshold != null) {
            float thresholdValue = Float.parseFloat(threshold);
            lowRatedFreelancersList.sort((f1, f2) -> Float.compare(f1.getRating(), f2.getRating()));

            warningMessageArea.setText("Freelancers sorted by rating (lowest first)\n" +
                    "Showing freelancers with rating below: " + threshold + "\n" +
                    "Total low-rated freelancers: " + lowRatedFreelancersList.size());
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