package com.example.oop_project_group19.Adnan;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TPasswordResetRequestsController {
    @FXML private Button backBtn;
    @FXML private TableColumn<PasswordResetRequest, String> emailCol;
    @FXML private TextField emailField;
    @FXML private CheckBox identityVerifiedCheckbox;
    @FXML private Button rejectRequestBtn;
    @FXML private TableColumn<PasswordResetRequest, String> requestIdCol;
    @FXML private TableColumn<PasswordResetRequest, LocalDateTime> requestTimeCol;
    @FXML private TableView<PasswordResetRequest> resetRequestsTable;
    @FXML private TextField securityAnswerField;
    @FXML private TextField securityQuestionField;
    @FXML private Button sendResetEmailBtn;
    @FXML private TableColumn<PasswordResetRequest, String> statusCol;
    @FXML private TableColumn<PasswordResetRequest, String> userIdCol;
    @FXML private TextField userIdField;
    @FXML private TableColumn<PasswordResetRequest, String> userTypeCol;
    @FXML private TableColumn<PasswordResetRequest, String> usernameCol;
    @FXML private TextField usernameField;
    @FXML private Button verifyIdentityBtn;

    private Scene scene;
    private ObservableList<PasswordResetRequest> resetRequestList = FXCollections.observableArrayList();

    public void initialize() {
        requestIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRequestId()));
        userIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        usernameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        userTypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserType()));
        requestTimeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRequestTime()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        loadSampleData();
        resetRequestsTable.setItems(resetRequestList);

        resetRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                userIdField.setText(newSelection.getUserId());
                usernameField.setText(newSelection.getUsername());
                emailField.setText(newSelection.getEmail());
                securityQuestionField.setText("What is your mother's maiden name?");
            }
        });
    }

    private void loadSampleData() {
        resetRequestList.add(new PasswordResetRequest("PR001", "U001", "naim_ur", "naimur@email.com", "Client"));
        resetRequestList.add(new PasswordResetRequest("PR002", "U002", "sami_ra", "samira@email.com", "Freelancer"));
        resetRequestList.add(new PasswordResetRequest("PR003", "U003", "ad_nan", "adnan@email.com", "Client"));
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
    }

    @FXML
    void handleRejectRequest(ActionEvent event) {
        PasswordResetRequest selected = resetRequestsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Rejected");
            resetRequestsTable.refresh();
            System.out.println("Request rejected: " + selected.getRequestId());
        }
    }

    @FXML
    void handleSendResetEmail(ActionEvent event) {
        PasswordResetRequest selected = resetRequestsTable.getSelectionModel().getSelectedItem();
        if (selected != null && identityVerifiedCheckbox.isSelected()) {
            selected.setStatus("Email Sent");
            resetRequestsTable.refresh();
            System.out.println("Reset email sent to: " + selected.getEmail());
        }
    }

    @FXML
    void handleVerifyIdentity(ActionEvent event) {
        if (!securityAnswerField.getText().isEmpty()) {
            identityVerifiedCheckbox.setSelected(true);
            System.out.println("Identity verified");
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