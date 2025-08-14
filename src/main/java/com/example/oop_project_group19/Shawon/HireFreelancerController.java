package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class HireFreelancerController {

    @FXML
    private TextField bidAmountField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmHireButton;

    @FXML
    private DatePicker deliveryDeadlinePicker;

    @FXML
    private ToggleGroup durationGroup;

    @FXML
    private RadioButton fixedTermRadio;

    @FXML
    private TextField freelancerNameField;

    @FXML
    private RadioButton hourlyRadio;

    @FXML
    private TextArea instructionsArea;

    @FXML
    private CheckBox milestonePaymentCheckBox;

    @FXML
    private CheckBox nondisclosureCheckBox;

    @FXML
    private RadioButton ongoingRadio;

    @FXML
    private ComboBox<String> paymentMethodCombo;

    @FXML
    private TextField projectTitleField;

    @FXML
    private Label statusLabel;

    private Scene scene;

    @FXML
    public void initialize() {
        paymentMethodCombo.getItems().addAll(
                "PayPal",
                "Bank Transfer",
                "Credit Card",
                "Escrow Service"
        );
    }

    @FXML
    void handleCancel(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleConfirmHire(ActionEvent event) {
        String freelancerName = freelancerNameField.getText();
        String projectTitle = projectTitleField.getText();
        String bidAmount = bidAmountField.getText();

        if (freelancerName.isEmpty() || projectTitle.isEmpty() || bidAmount.isEmpty() ||
                deliveryDeadlinePicker.getValue() == null) {
            statusLabel.setText("Please fill all required fields");
            return;
        }

        if (durationGroup.getSelectedToggle() == null) {
            statusLabel.setText("Please select contract duration");
            return;
        }

        if (paymentMethodCombo.getSelectionModel().getSelectedItem() == null) {
            statusLabel.setText("Please select payment method");
            return;
        }

        statusLabel.setText("Freelancer availability verified. Hiring confirmed and " +
                freelancerName + " notified!");

        clearFields();
    }

    private void clearFields() {
        freelancerNameField.clear();
        projectTitleField.clear();
        bidAmountField.clear();
        instructionsArea.clear();
        deliveryDeadlinePicker.setValue(null);
        paymentMethodCombo.getSelectionModel().clearSelection();
        durationGroup.selectToggle(null);
        milestonePaymentCheckBox.setSelected(false);
        nondisclosureCheckBox.setSelected(false);
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