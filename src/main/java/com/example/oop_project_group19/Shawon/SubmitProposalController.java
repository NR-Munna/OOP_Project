package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubmitProposalController implements Initializable {

    @FXML private TextArea additionalInfoArea;
    @FXML private Button attachFilesButton;
    @FXML private Label attachedFilesLabel;
    @FXML private TextField bidAmountField;
    @FXML private Button cancelButton;
    @FXML private TextField clientBudgetField;
    @FXML private TextArea coverLetterArea;
    @FXML private ComboBox<String> deliveryTimeCombo;
    @FXML private TextField jobTitleField;
    @FXML private CheckBox milestonesCheckBox;
    @FXML private Label statusLabel;
    @FXML private Button submitButton;

    private Scene scene;
    private Job currentJob;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deliveryTimeCombo.setItems(FXCollections.observableArrayList(
                "1-3 days", "1 week", "2 weeks", "1 month", "2-3 months"
        ));

        jobTitleField.setEditable(false);
        clientBudgetField.setEditable(false);
    }

    public void setJobDetails(Job job) {
        this.currentJob = job;
        jobTitleField.setText(job.getTitle());
        clientBudgetField.setText(String.valueOf(job.getBudget()));
    }

    @FXML
    void handleAttachFiles(ActionEvent event) {
        attachedFilesLabel.setText("Files attached: proposal.pdf, portfolio.zip");
        statusLabel.setText("Files attached successfully");
    }

    @FXML
    void handleCancel(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleSubmit(ActionEvent event) {
        if (validateProposal()) {
            String proposalId = "PROP" + System.currentTimeMillis();
            float bidAmount = Float.parseFloat(bidAmountField.getText());
            String coverLetter = coverLetterArea.getText();
            String deliveryTime = deliveryTimeCombo.getValue();

            Proposal proposal = new Proposal(proposalId, "FL001", "Adnan Freelancer", currentJob.getJobId(),
                    bidAmount, coverLetter, getDeliveryDays(deliveryTime), 4.5f);

            statusLabel.setText("Proposal submitted successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Proposal Submitted");
            alert.setContentText("Your proposal has been submitted to the client.");
            alert.showAndWait();

            switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
        }
    }

    private boolean validateProposal() {
        if (bidAmountField.getText().isEmpty()) {
            statusLabel.setText("Please enter bid amount");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        try {
            Float.parseFloat(bidAmountField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid bid amount format");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (coverLetterArea.getText().length() < 50) {
            statusLabel.setText("Cover letter must be at least 50 characters");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (deliveryTimeCombo.getValue() == null) {
            statusLabel.setText("Please select delivery time");
            statusLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

        return true;
    }

    private int getDeliveryDays(String deliveryTime) {
        switch (deliveryTime) {
            case "1-3 days": return 3;
            case "1 week": return 7;
            case "2 weeks": return 14;
            case "1 month": return 30;
            case "2-3 months": return 60;
            default: return 7;
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
