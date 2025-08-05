package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminSystemStatisticsController {

    @FXML
    private CheckBox activeClientsCheckBox;

    @FXML
    private CheckBox activeFreelancersCheckBox;

    @FXML
    private CheckBox averageProjectValueCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button clearAllButton;

    @FXML
    private CheckBox completedProjectsCheckBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Button exportStatsButton;

    @FXML
    private Button generateStatsButton;

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private CheckBox newUsersCheckBox;

    @FXML
    private CheckBox ongoingProjectsCheckBox;

    @FXML
    private CheckBox pendingProjectsCheckBox;

    @FXML
    private TableColumn<?, ?> percentageColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private Button selectAllButton;

    @FXML
    private TableColumn<?, ?> statisticNameColumn;

    @FXML
    private TableView<?> statisticsTable;

    @FXML
    private CheckBox totalProjectsCheckBox;

    @FXML
    private CheckBox totalRevenueCheckBox;

    @FXML
    private CheckBox totalUsersCheckBox;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClearAll(ActionEvent event) {

    }

    @FXML
    void handleExportStats(ActionEvent event) {

    }

    @FXML
    void handleGenerateStats(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSelectAll(ActionEvent event) {

    }

}
