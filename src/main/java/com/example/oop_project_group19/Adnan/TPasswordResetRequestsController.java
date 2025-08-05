package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TPasswordResetRequestsController {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TextField emailField;

    @FXML
    private CheckBox identityVerifiedCheckbox;

    @FXML
    private Button rejectRequestBtn;

    @FXML
    private TableColumn<?, ?> requestIdCol;

    @FXML
    private TableColumn<?, ?> requestTimeCol;

    @FXML
    private TableView<?> resetRequestsTable;

    @FXML
    private TextField securityAnswerField;

    @FXML
    private TextField securityQuestionField;

    @FXML
    private Button sendResetEmailBtn;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TextField userIdField;

    @FXML
    private TableColumn<?, ?> userTypeCol;

    @FXML
    private TableColumn<?, ?> usernameCol;

    @FXML
    private TextField usernameField;

    @FXML
    private Button verifyIdentityBtn;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleRejectRequest(ActionEvent event) {

    }

    @FXML
    void handleSendResetEmail(ActionEvent event) {

    }

    @FXML
    void handleVerifyIdentity(ActionEvent event) {

    }

}
