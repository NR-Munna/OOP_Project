package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CalculateSalaryController {

    @FXML
    private Button backBtn;

    @FXML
    private TextField baseSalaryField;

    @FXML
    private TableColumn<?, ?> bonusColumn;

    @FXML
    private TextField bonusField;

    @FXML
    private Button calculateBtn;

    @FXML
    private TableColumn<?, ?> deductionsColumn;

    @FXML
    private TextField deductionsField;

    @FXML
    private TableColumn<?, ?> employeeColumn;

    @FXML
    private TableColumn<?, ?> hoursColumn;

    @FXML
    private TextField hoursWorkedField;

    @FXML
    private TextField overtimeField;

    @FXML
    private TableView<?> pendingSalaryTable;

    @FXML
    private TableColumn<?, ?> positionColumn;

    @FXML
    private TableColumn<?, ?> rateColumn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Label selectedEmployeeLabel;

    @FXML
    private Button sendToFinanceBtn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleCalculate(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleSendToFinance(ActionEvent event) {

    }

}
