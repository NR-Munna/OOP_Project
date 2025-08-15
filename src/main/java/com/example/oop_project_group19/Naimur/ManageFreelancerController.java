package com.example.oop_project_group19.Naimur;

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
import java.util.Comparator;
import java.util.ResourceBundle;

public class ManageFreelancerController implements Initializable {

    @FXML
    private Button addNewBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Freelancer> freelancerTable;

    @FXML
    private CheckBox interviewedCheckBox;

    @FXML
    private TableColumn<Freelancer, Boolean> interviewedColumn;

    @FXML
    private TableColumn<Freelancer, LocalDate> joinDateColumn;

    @FXML
    private TableColumn<Freelancer, String> nameColumn;

    @FXML
    private TextField newEmailField;

    @FXML
    private TextField newNameField;

    @FXML
    private TextField newSkillsField;

    @FXML
    private TableColumn<Freelancer, Float> ratingColumn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button saveNewBtn;

    @FXML
    private TableColumn<Freelancer, String> skillsColumn;

    @FXML
    private ComboBox<String> sortComboBox;

    @FXML
    private TableColumn<Freelancer, String> statusColumn;

    private Scene scene;
    private ObservableList<Freelancer> freelancers;
    private boolean isAddingNew = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeSortComboBox();
        loadFreelancers();
        setNewFreelancerFieldsVisible(false);
    }

    private void initializeTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        interviewedColumn.setCellValueFactory(new PropertyValueFactory<>("interviewed"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
    }

    private void initializeSortComboBox() {
        ObservableList<String> sortOptions = FXCollections.observableArrayList(
                "Name", "Rating", "Join Date", "Status"
        );
        sortComboBox.setItems(sortOptions);
        sortComboBox.setValue("Name");
    }

    private void loadFreelancers() {
        freelancers = FXCollections.observableArrayList(
                new Freelancer("Christian Williams", "williams.doe@email.com", "Java, JavaScript, React", 4.5f, "Active", true, LocalDate.of(2023, 1, 15)),
                new Freelancer("Jane Smith", "jane.smith@email.com", "Python, Django, PostgreSQL", 4.8f, "Active", true, LocalDate.of(2023, 2, 20)),
                new Freelancer("Mike Johnson", "mike.johnson@email.com", "PHP, Laravel, MySQL", 4.2f, "Active", false, LocalDate.of(2023, 3, 10)),
                new Freelancer("Sarah Wilson", "sarah.wilson@email.com", "UI/UX Design, Figma", 4.7f, "Active", true, LocalDate.of(2023, 4, 5)),
                new Freelancer("David Brown", "david.brown@email.com", "Node.js, MongoDB, Express", 4.3f, "Inactive", true, LocalDate.of(2023, 5, 12))
        );
        freelancerTable.setItems(freelancers);
    }

    @FXML
    void onAddNewFreelancer(ActionEvent event) {
        if (!isAddingNew) {
            setNewFreelancerFieldsVisible(true);
            isAddingNew = true;
            addNewBtn.setText("Cancel");
        } else {
            setNewFreelancerFieldsVisible(false);
            clearNewFreelancerFields();
            isAddingNew = false;
            addNewBtn.setText("Add New");
        }
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onRefresh(ActionEvent event) {
        freelancerTable.setItems(freelancers);
        sortComboBox.setValue("Name");
    }

    @FXML
    void onSaveNewFreelancer(ActionEvent event) {
        if (validateNewFreelancer()) {
            boolean interviewed = interviewedCheckBox.isSelected();

            if (!interviewed) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Interview Required");
                confirmAlert.setHeaderText("Freelancer Not Interviewed");
                confirmAlert.setContentText("This freelancer has not been interviewed yet. Do you want to proceed?");

                if (confirmAlert.showAndWait().get() != ButtonType.OK) {
                    return;
                }
            }

            Freelancer newFreelancer = new Freelancer(
                    newNameField.getText().trim(),
                    newEmailField.getText().trim(),
                    newSkillsField.getText().trim(),
                    0.0f,
                    "Active",
                    interviewed,
                    LocalDate.now()
            );

            freelancers.add(newFreelancer);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Freelancer Added");
            alert.setHeaderText("Success");
            alert.setContentText("New freelancer " + newFreelancer.getName() + " has been added successfully.");
            alert.showAndWait();

            setNewFreelancerFieldsVisible(false);
            clearNewFreelancerFields();
            isAddingNew = false;
            addNewBtn.setText("Add New");
        }
    }

    @FXML
    void onSortChanged(ActionEvent event) {
        String sortBy = sortComboBox.getValue();
        ObservableList<Freelancer> sortedList = FXCollections.observableArrayList(freelancers);

        switch (sortBy) {
            case "Name":
                sortedList.sort(Comparator.comparing(Freelancer::getName));
                break;
            case "Rating":
                sortedList.sort(Comparator.comparing(Freelancer::getRating).reversed());
                break;
            case "Join Date":
                sortedList.sort(Comparator.comparing(Freelancer::getJoinDate).reversed());
                break;
            case "Status":
                sortedList.sort(Comparator.comparing(Freelancer::getStatus));
                break;
        }

        freelancerTable.setItems(sortedList);
    }

    private boolean validateNewFreelancer() {
        if (newNameField.getText().trim().isEmpty()) {
            showAlert("Name is required", Alert.AlertType.ERROR);
            return false;
        }
        if (newEmailField.getText().trim().isEmpty()) {
            showAlert("Email is required", Alert.AlertType.ERROR);
            return false;
        }
        if (newSkillsField.getText().trim().isEmpty()) {
            showAlert("Skills are required", Alert.AlertType.ERROR);
            return false;
        }

        String email = newEmailField.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            showAlert("Please enter a valid email address", Alert.AlertType.ERROR);
            return false;
        }

        for (Freelancer freelancer : freelancers) {
            if (freelancer.getEmail().equalsIgnoreCase(email)) {
                showAlert("A freelancer with this email already exists", Alert.AlertType.ERROR);
                return false;
            }
        }

        return true;
    }

    private void setNewFreelancerFieldsVisible(boolean visible) {
        newNameField.setVisible(visible);
        newEmailField.setVisible(visible);
        newSkillsField.setVisible(visible);
        interviewedCheckBox.setVisible(visible);
        saveNewBtn.setVisible(visible);
    }

    private void clearNewFreelancerFields() {
        newNameField.clear();
        newEmailField.clear();
        newSkillsField.clear();
        interviewedCheckBox.setSelected(false);
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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