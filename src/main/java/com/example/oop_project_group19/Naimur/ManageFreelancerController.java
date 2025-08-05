package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageFreelancerController {

    @FXML
    private Button addNewBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> freelancerTable;

    @FXML
    private CheckBox interviewedCheckBox;

    @FXML
    private TableColumn<?, ?> interviewedColumn;

    @FXML
    private TableColumn<?, ?> joinDateColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField newEmailField;

    @FXML
    private TextField newNameField;

    @FXML
    private TextField newSkillsField;

    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button saveNewBtn;

    @FXML
    private TableColumn<?, ?> skillsColumn;

    @FXML
    private ComboBox<?> sortComboBox;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    void onAddNewFreelancer(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onRefresh(ActionEvent event) {

    }

    @FXML
    void onSaveNewFreelancer(ActionEvent event) {

    }

    @FXML
    void onSortChanged(ActionEvent event) {

    }

}
