package com.example.oop_project_group19.Samira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSystemStatisticsController implements Initializable {

    @FXML
    private CheckBox activeClientsCheckBox;

    @FXML
    private CheckBox activeFreelancersCheckBox;

    @FXML
    private CheckBox averageProjectValueCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button clearAllButton;

    @FXML
    private CheckBox completedProjectsCheckBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Button exportStatsButton;

    @FXML
    private Button generateStatsButton;

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private CheckBox newUsersCheckBox;

    @FXML
    private CheckBox ongoingProjectsCheckBox;

    @FXML
    private CheckBox pendingProjectsCheckBox;

    @FXML
    private TableColumn<Statistics, String> percentageColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private Button selectAllButton;

    @FXML
    private TableColumn<Statistics, String> statisticNameColumn;

    @FXML
    private TableView<Statistics> statisticsTable;

    @FXML
    private CheckBox totalProjectsCheckBox;

    @FXML
    private CheckBox totalRevenueCheckBox;

    @FXML
    private CheckBox totalUsersCheckBox;

    @FXML
    private TableColumn<Statistics, String> valueColumn;

    private Scene scene;
    private ObservableList<Statistics> statisticsData;
    private List<CheckBox> checkBoxList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statisticNameColumn.setCellValueFactory(new PropertyValueFactory<>("statisticName"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        statisticsData = FXCollections.observableArrayList();
        statisticsTable.setItems(statisticsData);

        checkBoxList = new ArrayList<>();
        checkBoxList.add(totalUsersCheckBox);
        checkBoxList.add(activeClientsCheckBox);
        checkBoxList.add(activeFreelancersCheckBox);
        checkBoxList.add(totalProjectsCheckBox);
        checkBoxList.add(ongoingProjectsCheckBox);
        checkBoxList.add(completedProjectsCheckBox);
        checkBoxList.add(pendingProjectsCheckBox);
        checkBoxList.add(totalRevenueCheckBox);
        checkBoxList.add(averageProjectValueCheckBox);
        checkBoxList.add(newUsersCheckBox);

        errorLabel.setVisible(false);
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/AdminDashboard.fxml");
    }

    @FXML
    void handleClearAll(ActionEvent event) {
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setSelected(false);
        }
        statisticsData.clear();
        errorLabel.setVisible(false);
    }

    @FXML
    void handleExportStats(ActionEvent event) {
        if (statisticsData.isEmpty()) {
            errorLabel.setText("No statistics to export. Please generate statistics first.");
            errorLabel.setVisible(true);
            return;
        }

        errorLabel.setText("Statistics exported successfully!");
        errorLabel.setVisible(true);
    }

    @FXML
    void handleGenerateStats(ActionEvent event) {
        errorLabel.setVisible(false);

        boolean anySelected = checkBoxList.stream().anyMatch(CheckBox::isSelected);
        if (!anySelected) {
            errorLabel.setText("Please select at least one statistic to generate.");
            errorLabel.setVisible(true);
            return;
        }

        statisticsData.clear();

        if (totalUsersCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Total Users", "150", "100%"));
        }
        if (activeClientsCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Active Clients", "45", "30%"));
        }
        if (activeFreelancersCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Active Freelancers", "78", "52%"));
        }
        if (totalProjectsCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Total Projects", "89", "100%"));
        }
        if (ongoingProjectsCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Ongoing Projects", "34", "38.2%"));
        }
        if (completedProjectsCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Completed Projects", "42", "47.2%"));
        }
        if (pendingProjectsCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Pending Projects", "13", "14.6%"));
        }
        if (totalRevenueCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Total Revenue", "$245,000", "100%"));
        }
        if (averageProjectValueCheckBox.isSelected()) {
            statisticsData.add(new Statistics("Average Project Value", "$15,600", "-"));
        }
        if (newUsersCheckBox.isSelected()) {
            statisticsData.add(new Statistics("New Users (This Month)", "23", "15.3%"));
        }

        updateLastUpdatedLabel();
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        if (!statisticsData.isEmpty()) {
            handleGenerateStats(event);
        }
    }

    @FXML
    void handleSelectAll(ActionEvent event) {
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setSelected(true);
        }
    }

    private void updateLastUpdatedLabel() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        lastUpdatedLabel.setText("Last Updated: " + now.format(formatter));
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}