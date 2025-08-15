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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TChatSupportController {

    @FXML private Button acceptChatBtn;
    @FXML private Button backBtn;
    @FXML private TextArea chatHistoryArea;
    @FXML private TableView<ChatRequest> chatRequestsTable;
    @FXML private TextField chatStatusField;
    @FXML private TextField currentUserField;
    @FXML private Button endChatBtn;
    @FXML private TableColumn<ChatRequest, String> priorityColumn;
    @FXML private TextArea replyTextArea;
    @FXML private TableColumn<ChatRequest, String> requestIdColumn;
    @FXML private TableColumn<ChatRequest, LocalDateTime> requestTimeColumn;
    @FXML private Button sendBtn;
    @FXML private TextField sessionIdField;
    @FXML private TableColumn<ChatRequest, String> userIdColumn;
    @FXML private TableColumn<ChatRequest, String> usernameColumn;

    private Scene scene;
    private ObservableList<ChatRequest> chatRequestsList = FXCollections.observableArrayList();
    private ChatRequest currentChatSession = null;

    public void initialize() {
        requestIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRequestId()));
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        priorityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
        requestTimeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRequestTime()));

        loadSampleData();
        chatRequestsTable.setItems(chatRequestsList);

        chatStatusField.setText("Waiting for requests");
        chatHistoryArea.setText("No active chat session");

        chatRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                sessionIdField.setText("SESSION_" + newSelection.getRequestId());
                currentUserField.setText(newSelection.getUsername());
            }
        });
    }

    private void loadSampleData() {
        chatRequestsList.add(new ChatRequest("CR001", "U001", "Christian Williams", "High"));
        chatRequestsList.add(new ChatRequest("CR002", "U002", "Jane Smith", "Medium"));
        chatRequestsList.add(new ChatRequest("CR003", "U003", "Bob Johnson", "Low"));
        chatRequestsList.add(new ChatRequest("CR004", "U004", "Alice Brown", "High"));
    }

    @FXML
    void handleAcceptChat(ActionEvent event) {
        ChatRequest selected = chatRequestsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentChatSession = selected;
            selected.setStatus("In Progress");
            chatRequestsTable.refresh();

            chatStatusField.setText("Active Chat");
            currentUserField.setText(selected.getUsername());
            sessionIdField.setText("SESSION_" + selected.getRequestId());

            chatHistoryArea.setText("Chat session started with " + selected.getUsername() + "\n" +
                    "Session ID: SESSION_" + selected.getRequestId() + "\n" +
                    "Priority: " + selected.getPriority() + "\n" +
                    "Started at: " + LocalDateTime.now() + "\n\n" +
                    selected.getUsername() + ": Hello, I need help with my account.\n");

            System.out.println("Chat accepted with: " + selected.getUsername());
        }
    }

    @FXML
    void handleEndChat(ActionEvent event) {
        if (currentChatSession != null) {
            currentChatSession.setStatus("Completed");
            chatRequestsTable.refresh();

            chatHistoryArea.appendText("\n--- Chat session ended at " + LocalDateTime.now() + " ---\n");

            chatStatusField.setText("Chat Ended");
            currentUserField.clear();
            sessionIdField.clear();
            replyTextArea.clear();

            currentChatSession = null;
            System.out.println("Chat session ended");
        }
    }

    @FXML
    void handleSendMessage(ActionEvent event) {
        String message = replyTextArea.getText();
        if (!message.isEmpty() && currentChatSession != null) {
            chatHistoryArea.appendText("Support: " + message + "\n");

            String userResponse = generateUserResponse(message);
            chatHistoryArea.appendText(currentChatSession.getUsername() + ": " + userResponse + "\n");

            replyTextArea.clear();
            System.out.println("Message sent: " + message);
        }
    }

    private String generateUserResponse(String supportMessage) {
        String[] responses = {
                "Thank you for your help!",
                "That makes sense, let me try that.",
                "I understand now, thanks!",
                "Could you clarify that a bit more?",
                "Perfect, that solved my issue!",
                "I appreciate your quick response."
        };
        return responses[(int) (Math.random() * responses.length)];
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
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