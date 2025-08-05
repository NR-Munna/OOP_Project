package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminViewAllProjectsController {

    @FXML
    private Button applyFilterButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private Button clearFilterButton;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField maxBudgetTextField;

    @FXML
    private TextField minBudgetTextField;

    @FXML
    private TableColumn<?, ?> projectIdColumn;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private TableView<?> projectsTable;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusFilterComboBox;

    @FXML
    private Label totalBudgetLabel;

    @FXML
    private Label totalProjectsLabel;

    @FXML
    void handleApplyFilter(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearFilter(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

}
