package com.example.oop_project_group19.Naimur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewRatingsController {

    @FXML
    private Label averageRatingLabel;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> commentColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Button filterBtn;

    @FXML
    private TableColumn<?, ?> freelancerColumn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableColumn<?, ?> projectColumn;

    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private ComboBox<?> ratingFilterCombo;

    @FXML
    private TableView<?> ratingsTable;

    @FXML
    private Button refreshBtn;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    void onApplyFilter(ActionEvent event) {

    }

    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onRefresh(ActionEvent event) {

    }

}
