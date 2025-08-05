package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TrackExpensesController {

    @FXML
    private Button addExpenseBtn;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableView<?> categoryTotalsTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TextField expenseAmountField;

    @FXML
    private ComboBox<?> expenseCategoryCombo;

    @FXML
    private DatePicker expenseDatePicker;

    @FXML
    private TextField expenseDescriptionField;

    @FXML
    private TableView<?> expenseTable;

    @FXML
    private TableColumn<?, ?> totalAmountColumn;

    @FXML
    private TableColumn<?, ?> totalCategoryColumn;

    @FXML
    private Button updateTotalsBtn;

    @FXML
    void onAddExpense(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onUpdateTotals(ActionEvent event) {

    }

}
