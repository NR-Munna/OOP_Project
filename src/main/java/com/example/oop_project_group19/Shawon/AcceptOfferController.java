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

public class AcceptOfferController implements Initializable {

    @FXML private Button acceptOfferButton;
    @FXML private CheckBox availabilityCheckBox;
    @FXML private Button backButton;
    @FXML private TableColumn<JobOffer, String> clientColumn;
    @FXML private TextField contractAmountField;
    @FXML private TableColumn<JobOffer, LocalDate> deadlineColumn;
    @FXML private Button declineButton;
    @FXML private TextField deliveryDeadlineField;
    @FXML private TableColumn<JobOffer, String> jobTitleColumn;
    @FXML private TableColumn<JobOffer, Float> offerAmountColumn;
    @FXML private TableView<JobOffer> offersTable;
    @FXML private TextField paymentTermsField;
    @FXML private TextArea projectDescriptionArea;
    @FXML private TextField projectTitleField;
    @FXML private TableColumn<JobOffer, LocalDate> receivedDateColumn;
    @FXML private TableColumn<JobOffer, String> statusColumn;
    @FXML private Label statusLabel;
    @FXML private CheckBox termsCheckBox;

    private Scene scene;
    private ObservableList<JobOffer> jobOffers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        offerAmountColumn.setCellValueFactory(new PropertyValueFactory<>("offerAmount"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        receivedDateColumn.setCellValueFactory(new PropertyValueFactory<>("receivedDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadSampleOffers();
        offersTable.setItems(jobOffers);

        projectTitleField.setEditable(false);
        contractAmountField.setEditable(false);
        deliveryDeadlineField.setEditable(false);
        paymentTermsField.setEditable(false);

        offersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                projectTitleField.setText(newSelection.getJobTitle());
                contractAmountField.setText(String.valueOf(newSelection.getOfferAmount()));
                deliveryDeadlineField.setText(newSelection.getDeadline().toString());
                paymentTermsField.setText("50% upfront, 50% on completion");
                projectDescriptionArea.setText(newSelection.getDescription());
                acceptOfferButton.setDisable(false);
                declineButton.setDisable(false);
            }
        });
    }

    private void loadSampleOffers() {
        jobOffers.addAll(
                new JobOffer("OFF001", "E-commerce Website", "TechStart Inc", 2000.0f, LocalDate.now().plusDays(45), "Complete e-commerce solution with payment integration"),
                new JobOffer("OFF002", "Logo Design", "Creative Agency", 500.0f, LocalDate.now().plusDays(10), "Modern logo design for startup company"),
                new JobOffer("OFF003", "Content Strategy", "Marketing Pro", 800.0f, LocalDate.now().plusDays(20), "Develop comprehensive content marketing strategy")
        );
    }

    @FXML
    void handleAcceptOffer(ActionEvent event) {
        JobOffer selectedOffer = offersTable.getSelectionModel().getSelectedItem();
        if (selectedOffer != null) {
            if (!availabilityCheckBox.isSelected()) {
                statusLabel.setText("Please confirm your availability");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            if (!termsCheckBox.isSelected()) {
                statusLabel.setText("Please accept the terms and conditions");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            selectedOffer.setStatus("Accepted");
            statusLabel.setText("Offer accepted successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Offer Accepted");
            alert.setContentText("You have successfully accepted the job offer. The project status has been updated to 'In Progress'.");
            alert.showAndWait();

            offersTable.refresh();
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleDecline(ActionEvent event) {
        JobOffer selectedOffer = offersTable.getSelectionModel().getSelectedItem();
        if (selectedOffer != null) {
            selectedOffer.setStatus("Declined");
            statusLabel.setText("Offer declined");
            statusLabel.setStyle("-fx-text-fill: orange;");
            offersTable.refresh();
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
