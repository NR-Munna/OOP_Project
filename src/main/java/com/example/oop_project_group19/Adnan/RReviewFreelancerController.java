package com.example.oop_project_group19.Adnan;

import javafx.beans.property.SimpleFloatProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class RReviewFreelancerController {

    @FXML private Button approveBtn;
    @FXML private TextArea assessmentNotes;
    @FXML private TableColumn<FreelancerProfile, String> availabilityCol;
    @FXML private Button backBtn;
    @FXML private ComboBox<String> complexityCombo;
    @FXML private Label currentProjectsLabel;
    @FXML private Button declineBtn;
    @FXML private TextField durationField;
    @FXML private TableColumn<FreelancerProfile, String> experienceCol;
    @FXML private TableColumn<FreelancerProfile, String> freelancerIdCol;
    @FXML private TableView<FreelancerProfile> freelancersTable;
    @FXML private TableColumn<FreelancerProfile, String> nameCol;
    @FXML private TableColumn<FreelancerProfile, Float> ratingCol;
    @FXML private TextField requiredSkillsField;
    @FXML private TableColumn<FreelancerProfile, String> skillsCol;
    @FXML private Label workloadStatusLabel;

    private Scene scene;
    private ObservableList<FreelancerProfile> freelancersList = FXCollections.observableArrayList();

    public void initialize() {
        freelancerIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFreelancerId()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        skillsCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSkills()));
        ratingCol.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getRating()).asObject());
        experienceCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExperience()));
        availabilityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvailability()));

        ObservableList<String> complexityOptions = FXCollections.observableArrayList("Low", "Medium", "High", "Expert Level");
        complexityCombo.setItems(complexityOptions);

        loadSampleData();
        freelancersTable.setItems(freelancersList);

        freelancersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                assessmentNotes.setText("Freelancer Assessment:\n" +
                        "Name: " + newSelection.getName() + "\n" +
                        "Skills: " + newSelection.getSkills() + "\n" +
                        "Experience: " + newSelection.getExperience() + "\n" +
                        "Rating: " + newSelection.getRating() + "\n" +
                        "Availability: " + newSelection.getAvailability());
                currentProjectsLabel.setText("Current Projects: 2");
                workloadStatusLabel.setText("Workload: Moderate");
            }
        });
    }

    private void loadSampleData() {
        freelancersList.add(new FreelancerProfile("FL001", "Adnan Developer", "Java, Python, React", 4.5f, "5 years", "Available", "john@email.com"));
        freelancersList.add(new FreelancerProfile("FL002", "Samira Designer", "UI/UX, Photoshop, Figma", 4.2f, "3 years", "Busy", "sarah@email.com"));
        freelancersList.add(new FreelancerProfile("FL003", "Shawon Coder", "JavaScript, Node.js, MongoDB", 3.8f, "4 years", "Available", "mike@email.com"));
        freelancersList.add(new FreelancerProfile("FL004", "Naimur Analyst", "Data Analysis, SQL, Excel", 4.7f, "6 years", "Available", "lisa@email.com"));
    }

    @FXML
    void handleApproveFreelancer(ActionEvent event) {
        FreelancerProfile selected = freelancersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String complexity = complexityCombo.getValue();
            String requiredSkills = requiredSkillsField.getText();
            String duration = durationField.getText();

            assessmentNotes.appendText("\n\nAPPROVAL DECISION:\n" +
                    "Freelancer: " + selected.getName() + " - APPROVED\n" +
                    "Project Complexity: " + (complexity != null ? complexity : "Not specified") + "\n" +
                    "Required Skills: " + (requiredSkills.isEmpty() ? "Not specified" : requiredSkills) + "\n" +
                    "Duration: " + (duration.isEmpty() ? "Not specified" : duration) + " weeks");
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ReviewerDashboard.fxml");
    }

    @FXML
    void handleDeclineFreelancer(ActionEvent event) {
        FreelancerProfile selected = freelancersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            assessmentNotes.appendText("\n\nDECLINE DECISION:\n" +
                    "Freelancer: " + selected.getName() + " - DECLINED\n" +
                    "Reason: Skills mismatch or workload concerns");
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
