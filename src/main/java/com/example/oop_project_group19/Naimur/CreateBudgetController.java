package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateBudgetController {

    @FXML
    private Button addCategoryBtn;

    @FXML
    private TableColumn<?, ?> allocatedAmountColumn;

    @FXML
    private TextField allocatedAmountField;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> budgetCategoriesTable;

    @FXML
    private TextArea budgetDescriptionArea;

    @FXML
    private DatePicker budgetEndDatePicker;

    @FXML
    private DatePicker budgetStartDatePicker;

    @FXML
    private TextField categoryField;

    @FXML
    private TableColumn<?, ?> categoryNameColumn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button createBudgetPlanBtn;

    @FXML
    private ComboBox<?> departmentCombo;

    @FXML
    private TableColumn<?, ?> remainingAmountColumn;

    @FXML
    private Button saveBudgetBtn;

    @FXML
    private TextField selectedDepartmentField;

    @FXML
    private TextField totalBudgetField;

    @FXML
    void onAddCategory(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onClear(ActionEvent event) {

    }

    @FXML
    void onCreateBudgetPlan(ActionEvent event) {

    }

    @FXML
    void onSaveBudget(ActionEvent event) {

    }

}
