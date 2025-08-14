package com.example.oop_project_group19.Shawon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TrackEarningsController implements Initializable {

    @FXML private TableColumn<Earning, Float> amountColumn;
    @FXML private TextField availableBalanceField;
    @FXML private TextField averageField;
    @FXML private Button backButton;
    @FXML private TableColumn<Earning, String> clientColumn;
    @FXML private TextField completedProjectsField;
    @FXML private TableColumn<Earning, LocalDate> dateColumn;
    @FXML private TableView<Earning> earningsTable;
    @FXML private TableColumn<Earning, Float> feeColumn;
    @FXML private TextField pendingPaymentsField;
    @FXML private ComboBox<String> periodCombo;
    @FXML private TableColumn<Earning, String> projectColumn;
    @FXML private Button refreshButton;
    @FXML private TableColumn<Earning, String> statusColumn;
    @FXML private TextField successRateField;
    @FXML private TextField thisMonthField;
    @FXML private TextField totalEarningsField;
    @FXML private Button withdrawButton;

    private Scene scene;
    private ObservableList<Earning> earnings = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        periodCombo.setItems(FXCollections.observableArrayList(
                "This Month", "Last Month", "Last 3 Months", "Last 6 Months", "This Year"
        ));
        periodCombo.setValue("This Month");

        loadSampleEarnings();
        earningsTable.setItems(earnings);
        updateSummaryFields();

        availableBalanceField.setEditable(false);
        totalEarningsField.setEditable(false);
        thisMonthField.setEditable(false);
        pendingPaymentsField.setEditable(false);
        completedProjectsField.setEditable(false);
        averageField.setEditable(false);
        successRateField.setEditable(false);
    }

    private void loadSampleEarnings() {
        earnings.addAll(
                new Earning("E001", "E-commerce Website", "TechStart Inc", 2000.0f, 200.0f, LocalDate.now().minusDays(5), "Paid"),
                new Earning("E002", "Logo Design", "Creative Agency", 500.0f, 50.0f, LocalDate.now().minusDays(15), "Paid"),
                new Earning("E003", "Content Strategy", "Marketing Pro", 800.0f, 80.0f, LocalDate.now().minusDays(3), "Pending"),
                new Earning("E004", "Website Redesign", "ABC Corp", 1500.0f, 150.0f, LocalDate.now().minusDays(20), "Paid")
        );
    }

    private void updateSummaryFields() {
        float totalEarnings = 0;
        float pendingPayments = 0;
        float thisMonthEarnings = 0;
        int completedProjects = 0;
        int paidProjects = 0;

        for (Earning earning : earnings) {
            totalEarnings += earning.getAmount() - earning.getFee();

            if ("Pending".equals(earning.getStatus())) {
                pendingPayments += earning.getAmount() - earning.getFee();
            } else {
                paidProjects++;
            }

            if (earning.getDate().getMonth() == LocalDate.now().getMonth()) {
                thisMonthEarnings += earning.getAmount() - earning.getFee();
            }

            completedProjects++;
        }

        totalEarningsField.setText(String.format("$%.2f", totalEarnings));
        availableBalanceField.setText(String.format("$%.2f", totalEarnings - pendingPayments));
        thisMonthField.setText(String.format("$%.2f", thisMonthEarnings));
        pendingPaymentsField.setText(String.format("$%.2f", pendingPayments));
        completedProjectsField.setText(String.valueOf(completedProjects));

        if (completedProjects > 0) {
            averageField.setText(String.format("$%.2f", totalEarnings / completedProjects));
            successRateField.setText(String.format("%.1f%%", (paidProjects * 100.0f) / completedProjects));
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/FreelancerDashboard.fxml");
    }

    @FXML
    void handlePeriodFilter(ActionEvent event) {
        String selectedPeriod = periodCombo.getValue();
        ObservableList<Earning> filteredEarnings = FXCollections.observableArrayList();

        LocalDate filterDate = LocalDate.now();
        switch (selectedPeriod) {
            case "This Month":
                filterDate = LocalDate.now().withDayOfMonth(1);
                break;
            case "Last Month":
                filterDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                break;
            case "Last 3 Months":
                filterDate = LocalDate.now().minusMonths(3);
                break;
            case "Last 6 Months":
                filterDate = LocalDate.now().minusMonths(6);
                break;
            case "This Year":
                filterDate = LocalDate.now().withDayOfYear(1);
                break;
        }

        for (Earning earning : earnings) {
            if (earning.getDate().isAfter(filterDate) || earning.getDate().isEqual(filterDate)) {
                filteredEarnings.add(earning);
            }
        }

        earningsTable.setItems(filteredEarnings);
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        loadSampleEarnings();
        earningsTable.setItems(earnings);
        updateSummaryFields();
        periodCombo.setValue("This Month");
    }

    @FXML
    void handleWithdraw(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/oop_project_group19/Shawon/RequestWithdrawal.fxml"));
            Parent root = loader.load();
            RequestWithdrawalController controller = loader.getController();
            //controller.setAvailableBalance(availableBalanceField.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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