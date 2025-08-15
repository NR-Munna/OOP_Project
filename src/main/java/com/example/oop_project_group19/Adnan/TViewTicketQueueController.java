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
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

public class TViewTicketQueueController {
    @FXML private Button applyFiltersBtn;
    @FXML private Button backBtn;
    @FXML private TableColumn<Ticket, String> categoryCol;
    @FXML private ComboBox<String> categoryFilter;
    @FXML private TableColumn<Ticket, String> clientCol;
    @FXML private TableColumn<Ticket, LocalDateTime> creationDateCol;
    @FXML private TableColumn<Ticket, String> priorityCol;
    @FXML private ComboBox<String> priorityFilter;
    @FXML private ComboBox<String> sortFilter;
    @FXML private TableColumn<Ticket, String> statusCol;
    @FXML private TableColumn<Ticket, String> subjectCol;
    @FXML private TableColumn<Ticket, String> ticketIdCol;
    @FXML private TableView<Ticket> ticketsTable;
    @FXML private Button viewDetailsBtn;

    private Scene scene;
    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public void initialize() {
        ticketIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicketId()));
        subjectCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubject()));
        clientCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient()));
        priorityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        categoryCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        creationDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreationDate()));

        priorityFilter.setItems(FXCollections.observableArrayList("All", "High", "Medium", "Low"));
        categoryFilter.setItems(FXCollections.observableArrayList("All", "Technical", "Payment", "Account", "General"));
        sortFilter.setItems(FXCollections.observableArrayList("Creation Date", "Priority", "Status"));

        loadSampleData();
        ticketsTable.setItems(ticketList);
    }

    private void loadSampleData() {
        ticketList.add(new Ticket("T001", "Login Issues", "Naimur Rahman", "High", "Open", "Technical"));
        ticketList.add(new Ticket("T002", "Payment Problem", "Smaira Fatima", "Medium", "In Progress", "Payment"));
        ticketList.add(new Ticket("T003", "Account Access", "Abir Hossain", "Low", "Resolved", "Account"));
    }

    @FXML
    void handleApplyFilters(ActionEvent event) {
        String selectedPriority = priorityFilter.getValue();
        String selectedCategory = categoryFilter.getValue();

        ObservableList<Ticket> filteredList = FXCollections.observableArrayList();

        for (Ticket ticket : ticketList) {
            boolean matchesPriority = selectedPriority == null || selectedPriority.equals("All") || ticket.getPriority().equals(selectedPriority);
            boolean matchesCategory = selectedCategory == null || selectedCategory.equals("All") || ticket.getCategory().equals(selectedCategory);

            if (matchesPriority && matchesCategory) {
                filteredList.add(ticket);
            }
        }

        ticketsTable.setItems(filteredList);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/TechnicalSupportDashboard.fxml");
    }

    @FXML
    void handleViewDetails(ActionEvent event) {
        Ticket selected = ticketsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Viewing details for ticket: " + selected.getTicketId());
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