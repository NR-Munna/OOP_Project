package com.example.oop_project_group19.Shawon;

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

public class BrowseJobsController implements Initializable {

    @FXML private Button backButton;
    @FXML private TableColumn<Job, Float> budgetColumn;
    @FXML private ComboBox<String> categoryCombo;
    @FXML private Button clearButton;
    @FXML private TableColumn<Job, String> clientColumn;
    @FXML private TableColumn<Job, LocalDate> deadlineColumn;
    @FXML private TextArea jobDescriptionArea;
    @FXML private TableColumn<Job, String> jobTitleColumn;
    @FXML private TableView<Job> jobsTable;
    @FXML private TextField maxBudgetField;
    @FXML private TextField minBudgetField;
    @FXML private TableColumn<Job, LocalDate> postedDateColumn;
    @FXML private Button searchButton;
    @FXML private TableColumn<Job, String> skillsColumn;
    @FXML private TextField skillsFilterField;
    @FXML private Button submitProposalButton;

    private Scene scene;
    private ObservableList<Job> jobs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        postedDateColumn.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));

        categoryCombo.setItems(FXCollections.observableArrayList(
                "Web Development", "Mobile Development", "Design", "Writing", "Marketing", "Data Entry"
        ));

        loadSampleJobs();
        jobsTable.setItems(jobs);

        jobsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                jobDescriptionArea.setText(newSelection.getDescription());
                submitProposalButton.setDisable(false);
            }
        });
    }

    private void loadSampleJobs() {
        jobs.addAll(
                new Job("J001", "Website Redesign", "Complete website redesign project", "ABC Corp", "HTML, CSS, JavaScript", 1500.0f, LocalDate.now().plusDays(30), "Web Development"),
                new Job("J002", "Mobile App UI", "Design mobile app user interface", "XYZ Tech", "UI/UX, Figma", 800.0f, LocalDate.now().plusDays(15), "Design"),
                new Job("J003", "Content Writing", "Write blog articles", "Content Co", "Writing, SEO", 300.0f, LocalDate.now().plusDays(7), "Writing")
        );
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleClear(ActionEvent event) {
        skillsFilterField.clear();
        minBudgetField.clear();
        maxBudgetField.clear();
        categoryCombo.setValue(null);
        jobsTable.setItems(jobs);
    }

    @FXML
    void handleSearch(ActionEvent event) {
        ObservableList<Job> filteredJobs = FXCollections.observableArrayList();
        String skillFilter = skillsFilterField.getText().toLowerCase();
        String category = categoryCombo.getValue();
        String minBudget = minBudgetField.getText();
        String maxBudget = maxBudgetField.getText();

        for (Job job : jobs) {
            boolean matchesSkill = skillFilter.isEmpty() || job.getSkills().toLowerCase().contains(skillFilter);
            boolean matchesCategory = category == null || job.getCategory().equals(category);
            boolean matchesBudget = true;

            if (!minBudget.isEmpty()) {
                try {
                    matchesBudget = job.getBudget() >= Float.parseFloat(minBudget);
                } catch (NumberFormatException e) {}
            }

            if (!maxBudget.isEmpty() && matchesBudget) {
                try {
                    matchesBudget = job.getBudget() <= Float.parseFloat(maxBudget);
                } catch (NumberFormatException e) {}
            }

            if (matchesSkill && matchesCategory && matchesBudget) {
                filteredJobs.add(job);
            }
        }
        jobsTable.setItems(filteredJobs);
    }

    @FXML
    void handleSubmitProposal(ActionEvent event) {
        Job selectedJob = jobsTable.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/oop_project_group19/Shawon/SubmitProposal.fxml"));
                Parent root = loader.load();
                SubmitProposalController controller = loader.getController();
                //controller.setJobDetails(selectedJob);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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