package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewHistoryController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private TextField completedProjectsField;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> freelancerColumn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableView<?> projectHistoryTable;

    @FXML
    private TextArea projectSummaryArea;

    @FXML
    private TableColumn<?, ?> projectTitleColumn;

    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private ComboBox<?> statusFilterCombo;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TextField totalProjectsField;

    @FXML
    private TextField totalSpentField;

    @FXML
    private Button viewDetailsButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @Deprecated
    void handleExport(ActionEvent event) {

    }

    @Deprecated
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleStatusFilter(ActionEvent event) {

    }

    @FXML
    void handleViewDetails(ActionEvent event) {

    }

}
