package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TrackEarningsController {

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TextField availableBalanceField;

    @FXML
    private TextField averageField;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TextField completedProjectsField;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableView<?> earningsTable;

    @FXML
    private TableColumn<?, ?> feeColumn;

    @FXML
    private TextField pendingPaymentsField;

    @FXML
    private ComboBox<?> periodCombo;

    @FXML
    private TableColumn<?, ?> projectColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TextField successRateField;

    @FXML
    private TextField thisMonthField;

    @FXML
    private TextField totalEarningsField;

    @FXML
    private Button withdrawButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @Deprecated
    void handleExport(ActionEvent event) {

    }

    @FXML
    void handlePeriodFilter(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleWithdraw(ActionEvent event) {

    }

}
