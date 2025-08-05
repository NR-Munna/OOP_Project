package com.example.oop_project_group19.Shawon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AcceptOfferController {

    @FXML
    private Button acceptOfferButton;

    @FXML
    private CheckBox availabilityCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TextField contractAmountField;

    @FXML
    private TableColumn<?, ?> deadlineColumn;

    @FXML
    private Button declineButton;

    @FXML
    private TextField deliveryDeadlineField;

    @FXML
    private TableColumn<?, ?> jobTitleColumn;

    @FXML
    private TableColumn<?, ?> offerAmountColumn;

    @FXML
    private TableView<?> offersTable;

    @FXML
    private TextField paymentTermsField;

    @FXML
    private TextArea projectDescriptionArea;

    @FXML
    private TextField projectTitleField;

    @FXML
    private TableColumn<?, ?> receivedDateColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox termsCheckBox;

    @FXML
    void handleAcceptOffer(ActionEvent event) {

    }

    @FXML
    void handleBack(ActionEvent event) {

    }

    @FXML
    void handleDecline(ActionEvent event) {

    }

}
