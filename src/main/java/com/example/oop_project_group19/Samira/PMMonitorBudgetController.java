package com.example.oop_project_group19.Samira;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PMMonitorBudgetController {

    @FXML
    private TableColumn<?, ?> allocatedColumn;

    @FXML
    private TextField allocatedTextField;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> budgetTable;

    @FXML
    private Label budgetUtilizationLabel;

    @FXML
    private Button calculateButton;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private TextField contingencyTextField;

    @FXML
    private TextField designCostTextField;

    @FXML
    private TextField developmentCostTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button exportButton;

    @FXML
    private TextField miscTextField;

    @FXML
    private ComboBox<?> projectComboBox;

    @FXML
    private TableColumn<?, ?> projectNameColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> remainingColumn;

    @FXML
    private Button saveButton;

    @FXML
    private TableColumn<?, ?> spentColumn;

    @FXML
    private TextField spentTextField;

    @FXML
    private TableColumn<?, ?> statusBudgetColumn;

    @FXML
    private TextField testingCostTextField;

    @FXML
    private TableColumn<?, ?> totalBudgetColumn;

    @FXML
    private TextField totalBudgetTextField;

    @FXML
    private Label totalProjectBudgetLabel;

    @FXML
    private Label totalSpentLabel;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleCalculate(ActionEvent event) {

    }

    @FXML
    void handleExport(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {

    }

}
