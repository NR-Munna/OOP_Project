package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ViewProposalsController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> deliveryTimeColumn;

    @FXML
    private TableColumn<?, ?> freelancerNameColumn;

    @FXML
    private Button hireButton;

    @FXML
    private ComboBox<?> jobComboBox;

    @FXML
    private TableColumn<?, ?> proposalAmountColumn;

    @FXML
    private TextArea proposalDetailsArea;

    @FXML
    private TableColumn<?, ?> proposalTextColumn;

    @FXML
    private TableView<?> proposalsTable;

    @FXML
    private TableColumn<?, ?> ratingColumn;

    @FXML
    private Button viewProfileButton;

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleHire(ActionEvent event) {

    }

    @FXML
    void handleJobSelection(ActionEvent event) {

    }

    @Deprecated
    void handleRefreshJobs(ActionEvent event) {

    }

    @FXML
    void handleViewProfile(ActionEvent event) {

    }

}
