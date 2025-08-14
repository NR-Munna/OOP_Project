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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatController {

    @FXML
    private Button attachFileButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea chatHistoryArea;

    @FXML
    private Button clearChatButton;

    @FXML
    private TextField freelancerNameField;

    @FXML
    private CheckBox markUrgentCheckBox;

    @FXML
    private TextArea messageInputArea;

    @FXML
    private Label onlineStatusLabel;

    @FXML
    private ComboBox<Project> projectComboBox;

    @FXML
    private Button sendButton;

    private Scene scene;

    @FXML
    public void initialize() {
        projectComboBox.getItems().addAll(
                new Project("P1", "Website Development", "C1", "F1", "John Smith", null, null, 1500.0, "Active"),
                new Project("P2", "Mobile App", "C1", "F2", "Sarah Johnson", null, null, 2000.0, "Active"),
                new Project("P3", "Logo Design", "C1", "F3", "Mike Brown", null, null, 500.0, "Active")
        );

        freelancerNameField.setEditable(false);
    }

    @FXML
    void handleAttachFile(ActionEvent event) {
        chatHistoryArea.appendText("\n[File attachment functionality - File selected]\n");
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/ClientDashboard.fxml");
    }

    @FXML
    void handleClearChat(ActionEvent event) {
        chatHistoryArea.clear();
        onlineStatusLabel.setText("Chat cleared");
    }

    @FXML
    void handleProjectSelection(ActionEvent event) {
        Project selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            onlineStatusLabel.setText("Project selected - Chat opened");
            freelancerNameField.setText(selectedProject.getFreelancerName());
            chatHistoryArea.setText("Chat history loaded for: " + selectedProject.getTitle() +
                    "\nFreelancer: " + selectedProject.getFreelancerName() + "\n\n" +
                    selectedProject.getFreelancerName() + " [Yesterday 14:30]: Hello! I've started working on the project.\n" +
                    "Client [Yesterday 14:35]: Great! Let me know if you need any clarifications.\n");
        }
    }

    @FXML
    void handleSendMessage(ActionEvent event) {
        String message = messageInputArea.getText().trim();
        if (message.isEmpty()) {
            onlineStatusLabel.setText("Please enter a message");
            return;
        }

        if (projectComboBox.getSelectionModel().getSelectedItem() == null) {
            onlineStatusLabel.setText("Please select a project first");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String urgentMark = markUrgentCheckBox.isSelected() ? " [URGENT]" : "";

        chatHistoryArea.appendText("\nClient [" + timestamp + "]" + urgentMark + ": " + message);

        messageInputArea.clear();
        markUrgentCheckBox.setSelected(false);
        onlineStatusLabel.setText("Message sent and stored");
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