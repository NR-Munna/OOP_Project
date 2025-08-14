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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleInterviewController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> freelancerComboBox;

    @FXML
    private RadioButton inPersonRadio;

    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    private TextField interviewTimeField;

    @FXML
    private ToggleGroup interviewType;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private RadioButton onlineRadio;

    @FXML
    private Button scheduleBtn;

    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> freelancers = FXCollections.observableArrayList(
                "John Doe", "Jane Smith", "Mike Johnson", "Sarah Wilson", "David Brown"
        );
        freelancerComboBox.setItems(freelancers);

        inPersonRadio.setToggleGroup(interviewType);
        onlineRadio.setToggleGroup(interviewType);
        inPersonRadio.setSelected(true);
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onCancel(ActionEvent event) {
        clearFields();
    }

    @FXML
    void onScheduleInterview(ActionEvent event) {
        if (validateInput()) {
            String freelancer = freelancerComboBox.getValue();
            String date = interviewDatePicker.getValue() != null ? interviewDatePicker.getValue().toString() : "";
            String time = interviewTimeField.getText();
            String type = inPersonRadio.isSelected() ? "In-Person" : "Online";
            String notes = notesTextArea.getText();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Interview Scheduled");
            alert.setHeaderText("Success");
            alert.setContentText("Interview scheduled for " + freelancer + " on " + date + " at " + time + " (" + type + ")");
            alert.showAndWait();

            clearFields();
        }
    }

    private boolean validateInput() {
        if (freelancerComboBox.getValue() == null) {
            showAlert("Please select a freelancer");
            return false;
        }
        if (interviewDatePicker.getValue() == null) {
            showAlert("Please select interview date");
            return false;
        }
        if (interviewTimeField.getText().trim().isEmpty()) {
            showAlert("Please enter interview time");
            return false;
        }
        return true;
    }

    private void clearFields() {
        freelancerComboBox.setValue(null);
        interviewDatePicker.setValue(null);
        interviewTimeField.clear();
        notesTextArea.clear();
        inPersonRadio.setSelected(true);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Input Required");
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