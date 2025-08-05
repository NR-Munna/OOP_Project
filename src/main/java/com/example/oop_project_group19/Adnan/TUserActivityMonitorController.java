package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TUserActivityMonitorController {

    @FXML
    private TextArea activityLogArea;

    @FXML
    private TableView<?> activityTable;

    @FXML
    private ComboBox<?> activityTypeCombo;

    @FXML
    private TextField alertMessageField;

    @FXML
    private ComboBox<?> alertTypeCombo;

    @FXML
    private Button blockUserBtn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> failedAttemptsColumn;

    @FXML
    private TableColumn<?, ?> ipAddressColumn;

    @FXML
    private TableColumn<?, ?> lastAttemptColumn;

    @FXML
    private Button resetAttemptsBtn;

    @FXML
    private TextField selectedFailedAttemptsField;

    @FXML
    private TextField selectedUserIdField;

    @FXML
    private TextField selectedUsernameField;

    @FXML
    private Button sendAlertBtn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    void handleBlockUser(ActionEvent event) {

    }

    @FXML
    void handleResetAttempts(ActionEvent event) {

    }

    @FXML
    void handleSendAlert(ActionEvent event) {

    }

}
