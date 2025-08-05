package com.example.oop_project_group19.Adnan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TViewTicketQueueController {

    @FXML
    private Button applyFiltersBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> categoryCol;

    @FXML
    private ComboBox<?> categoryFilter;

    @FXML
    private TableColumn<?, ?> clientCol;

    @FXML
    private TableColumn<?, ?> creationDateCol;

    @FXML
    private TableColumn<?, ?> priorityCol;

    @FXML
    private ComboBox<?> priorityFilter;

    @FXML
    private ComboBox<?> sortFilter;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableColumn<?, ?> subjectCol;

    @FXML
    private TableColumn<?, ?> ticketIdCol;

    @FXML
    private TableView<?> ticketsTable;

    @FXML
    private Button viewDetailsBtn;

    @FXML
    void handleApplyFilters(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleViewDetails(ActionEvent event) {

    }

}
