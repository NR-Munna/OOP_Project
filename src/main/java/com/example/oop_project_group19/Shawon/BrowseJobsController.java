package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BrowseJobsController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> budgetColumn;

    @FXML
    private ComboBox<?> categoryCombo;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> deadlineColumn;

    @FXML
    private TextArea jobDescriptionArea;

    @FXML
    private TableColumn<?, ?> jobTitleColumn;

    @FXML
    private TableView<?> jobsTable;

    @FXML
    private TextField maxBudgetField;

    @FXML
    private TextField minBudgetField;

    @FXML
    private TableColumn<?, ?> postedDateColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<?, ?> skillsColumn;

    @FXML
    private TextField skillsFilterField;

    @FXML
    private Button submitProposalButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleSubmitProposal(ActionEvent event) {

    }

}
