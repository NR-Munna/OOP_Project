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

public class ViewProposalsController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Proposal, Integer> deliveryTimeColumn;

    @FXML
    private TableColumn<Proposal, String> freelancerNameColumn;

    @FXML
    private Button hireButton;

    @FXML
    private ComboBox<String> jobComboBox;

    @FXML
    private TableColumn<Proposal, Double> proposalAmountColumn;

    @FXML
    private TextArea proposalDetailsArea;

    @FXML
    private TableColumn<Proposal, String> proposalTextColumn;

    @FXML
    private TableView<Proposal> proposalsTable;

    @FXML
    private TableColumn<Proposal, Double> ratingColumn;

    @FXML
    private Button viewProfileButton;

    private Scene scene;
    private ObservableList<Proposal> proposalsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        jobComboBox.getItems().addAll(
                "Website Development Project",
                "Mobile App Development",
                "Logo Design",
                "Content Writing for Blog"
        );

        freelancerNameColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerName"));
        proposalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("proposalAmount"));
        proposalTextColumn.setCellValueFactory(new PropertyValueFactory<>("proposalText"));
        deliveryTimeColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryTime"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("freelancerRating"));

        proposalsTable.setItems(proposalsList);

        proposalsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                proposalDetailsArea.setText("Proposal Details:\n\n" +
                        "Freelancer: " + newSelection.getFreelancerName() + "\n" +
                        "Amount: $" + newSelection.getProposalAmount() + "\n" +
                        "Delivery: " + newSelection.getDeliveryTime() + " days\n" +
                        "Rating: " + newSelection.getFreelancerRating() + "/5\n\n" +
                        "Proposal: " + newSelection.getProposalText());
            }
        });
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleHire(ActionEvent event) {
        Proposal selectedProposal = proposalsTable.getSelectionModel().getSelectedItem();
        if (selectedProposal == null) {
            proposalDetailsArea.setText("Please select a proposal to hire");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hire Freelancer");
        alert.setHeaderText("Confirm Hiring");
        alert.setContentText("Are you sure you want to hire " + selectedProposal.getFreelancerName() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            proposalDetailsArea.setText("Freelancer " + selectedProposal.getFreelancerName() +
                    " hired successfully! Contract terms confirmed.");
        }
    }

    @FXML
    void handleJobSelection(ActionEvent event) {
        String selectedJob = jobComboBox.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            loadProposalsForJob(selectedJob);
            proposalDetailsArea.setText("Loading proposals for: " + selectedJob + "\nProposals fetched and displayed in table.");
        }
    }

    @FXML
    void handleViewProfile(ActionEvent event) {
        Proposal selectedProposal = proposalsTable.getSelectionModel().getSelectedItem();
        if (selectedProposal == null) {
            proposalDetailsArea.setText("Please select a proposal to view profile");
            return;
        }
        proposalDetailsArea.setText("Viewing profile for: " + selectedProposal.getFreelancerName() +
                "\nProfile opened in new window");
    }

    private void loadProposalsForJob(String jobTitle) {
        proposalsList.clear();
        proposalsList.addAll(
                new Proposal("P1", "F1", "John Smith", "J1", 1500.0, "I can complete this project with high quality", 15, 4.8),
                new Proposal("P2", "F2", "Sarah Johnson", "J1", 1200.0, "Experienced in similar projects", 12, 4.5),
                new Proposal("P3", "F3", "Mike Brown", "J1", 1800.0, "Premium quality guaranteed", 20, 4.9)
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
