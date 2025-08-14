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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CommunicateClientController implements Initializable {

    @FXML private Button attachFileButton;
    @FXML private Button backButton;
    @FXML private TextArea chatHistoryArea;
    @FXML private Button clearChatButton;
    @FXML private TextField clientNameField;
    @FXML private TextArea messageInputArea;
    @FXML private Label onlineStatusLabel;
    @FXML private ComboBox<String> projectComboBox;
    @FXML private Button sendButton;

    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectComboBox.setItems(FXCollections.observableArrayList(
                "E-commerce Website - TechStart Inc",
                "Logo Design - Creative Agency",
                "Content Strategy - Marketing Pro"
        ));

        clientNameField.setEditable(false);
        chatHistoryArea.setEditable(false);

        loadSampleChatHistory();
        onlineStatusLabel.setText("Online");
        onlineStatusLabel.setStyle("-fx-text-fill: green;");
    }

    private void loadSampleChatHistory() {
        String sampleChat = "[2024-01-15 10:30] Client: Hi, how is the project coming along?\n" +
                "[2024-01-15 10:32] You: Hi! The project is progressing well. I've completed the initial wireframes.\n" +
                "[2024-01-15 10:35] Client: Great! Can you send me a preview?\n" +
                "[2024-01-15 10:37] You: Sure, I'll upload the files in a moment.\n\n";
        chatHistoryArea.setText(sampleChat);
    }

    @FXML
    void handleAttachFile(ActionEvent event) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String message = "[" + currentTime + "] You: [FILE ATTACHED: project_preview.pdf]\n";
        chatHistoryArea.appendText(message);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handleClearChat(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Clear Chat");
        confirmation.setHeaderText("Clear Chat History");
        confirmation.setContentText("Are you sure you want to clear the chat history?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            chatHistoryArea.clear();
        }
    }

    @FXML
    void handleProjectSelection(ActionEvent event) {
        String selectedProject = projectComboBox.getValue();
        if (selectedProject != null) {
            String clientName = selectedProject.split(" - ")[1];
            clientNameField.setText(clientName);
            chatHistoryArea.clear();
            loadProjectSpecificChat(clientName);
        }
    }

    private void loadProjectSpecificChat(String clientName) {
        String projectChat = "[2024-01-15 09:00] " + clientName + ": Welcome to the project!\n" +
                "[2024-01-15 09:05] You: Thank you! I'm excited to work with you.\n\n";
        chatHistoryArea.setText(projectChat);
    }

    @FXML
    void handleSendMessage(ActionEvent event) {
        String message = messageInputArea.getText().trim();
        if (!message.isEmpty()) {
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String formattedMessage = "[" + currentTime + "] You: " + message + "\n";
            chatHistoryArea.appendText(formattedMessage);
            messageInputArea.clear();

            chatHistoryArea.setScrollTop(Double.MAX_VALUE);
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
