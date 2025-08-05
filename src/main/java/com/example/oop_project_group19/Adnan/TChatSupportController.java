package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TChatSupportController {

    @FXML
    private Button acceptChatBtn;

    @FXML
    private TextArea chatHistoryArea;

    @FXML
    private TableView<?> chatRequestsTable;

    @FXML
    private TextField chatStatusField;

    @FXML
    private TextField currentUserField;

    @FXML
    private Button endChatBtn;

    @FXML
    private TableColumn<?, ?> priorityColumn;

    @FXML
    private TextArea replyTextArea;

    @FXML
    private TableColumn<?, ?> requestIdColumn;

    @FXML
    private TableColumn<?, ?> requestTimeColumn;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField sessionIdField;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    void handleAcceptChat(ActionEvent event) {

    }

    @FXML
    void handleEndChat(ActionEvent event) {

    }

    @FXML
    void handleSendMessage(ActionEvent event) {

    }

}
