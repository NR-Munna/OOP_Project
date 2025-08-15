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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class TUpdateTicketStatusController {
    @FXML private TableColumn<Ticket, String> assignedToCol;
    @FXML private Button backBtn;
    @FXML private Button clearBtn;
    @FXML private TableColumn<Ticket, String> clientCol;
    @FXML private TableColumn<Ticket, String> priorityCol;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private TextArea resolutionDetailsArea;
    @FXML private TextField selectedTicketField;
    @FXML private TableColumn<Ticket, String> statusCol;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private TableColumn<Ticket, String> subjectCol;
    @FXML private TableColumn<Ticket, String> ticketIdCol;
    @FXML private TableView<Ticket> ticketsTable;
    @FXML private TextArea updateNotesArea;
    @FXML private Button updateStatusBtn;

    private Scene scene;
    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public void initialize() {
        ticketIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicketId()));
        subjectCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubject()));
        clientCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient()));
        priorityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        assignedToCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAssignedTo()));

        statusComboBox.setItems(FXCollections.observableArrayList("Open", "In Progress", "Resolved", "Closed"));
        priorityComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));

        loadSampleData();
        ticketsTable.setItems(ticketList);

        ticketsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTicketField.setText(newSelection.getTicketId());
                statusComboBox.setValue(newSelection.getStatus());
                priorityComboBox.setValue(newSelection.getPriority());
            }
        });
    }

    private void loadSampleData() {
        ticketList.add(new Ticket("T001", "Login Issues", "John Doe", "High", "Open", "Technical"));
        ticketList.add(new Ticket("T002", "Payment Problem", "Jane Smith", "Medium", "In Progress", "Payment"));
        ticketList.add(new Ticket("T003", "Account Access", "Bob Johnson", "Low", "Resolved", "Account"));
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
    }

    @FXML
    void handleClear(ActionEvent event) {
        selectedTicketField.clear();
        statusComboBox.setValue(null);
        priorityComboBox.setValue(null);
        updateNotesArea.clear();
        resolutionDetailsArea.clear();
        ticketsTable.getSelectionModel().clearSelection();
    }

    @FXML
    void handleUpdateStatus(ActionEvent event) {
        Ticket selected = ticketsTable.getSelectionModel().getSelectedItem();
        if (selected != null && statusComboBox.getValue() != null) {
            selected.setStatus(statusComboBox.getValue());
            ticketsTable.refresh();
            System.out.println("Status updated for ticket: " + selected.getTicketId());
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