package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class MonitorCashFlowController {

    @FXML
    private Button backBtn;

    @FXML
    private Button calculateCashFlowBtn;

    @FXML
    private TableView<?> cashFlowTable;

    @FXML
    private AnchorPane chartContainer;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableColumn<?, ?> expensesColumn;

    @FXML
    private Button generateChartBtn;

    @FXML
    private TableColumn<?, ?> incomeColumn;

    @FXML
    private TableColumn<?, ?> netCashFlowColumn;

    @FXML
    private TableColumn<?, ?> periodColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onCalculateCashFlow(ActionEvent event) {

    }

    @FXML
    void onGenerateChart(ActionEvent event) {

    }

}
