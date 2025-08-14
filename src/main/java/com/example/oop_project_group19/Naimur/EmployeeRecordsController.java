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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeRecordsController implements Initializable {

    @FXML
    private RadioButton activeRadio;

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private Button editRecordBtn;

    @FXML
    private ComboBox<String> employeeComboBox;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField fullNameField;

    @FXML
    private DatePicker hireDatePicker;

    @FXML
    private RadioButton inactiveRadio;

    @FXML
    private TextArea notesArea;

    @FXML
    private TextField positionField;

    @FXML
    private TextField salaryField;

    @FXML
    private Button saveBtn;

    @FXML
    private ToggleGroup statusGroup;

    private Scene scene;
    private Map<String, Employee> employees;
    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        initializeEmployees();
        setupRadioButtons();
        setFieldsEditable(false);
    }

    private void initializeComboBoxes() {
        ObservableList<String> employeeList = FXCollections.observableArrayList(
                "John Doe", "Jane Smith", "Mike Johnson", "Sarah Wilson", "David Brown"
        );
        employeeComboBox.setItems(employeeList);

        ObservableList<String> departments = FXCollections.observableArrayList(
                "Human Resources", "Finance", "Technical Support", "Project Management", "Development"
        );
        departmentComboBox.setItems(departments);
    }

    private void initializeEmployees() {
        employees = new HashMap<>();
        employees.put("John Doe", new Employee("EMP001", "John Doe", "HR Manager", "Human Resources", 55000.0f, LocalDate.of(2020, 1, 15), "Active", "Experienced HR professional"));
        employees.put("Jane Smith", new Employee("EMP002", "Jane Smith", "Senior Developer", "Development", 75000.0f, LocalDate.of(2019, 3, 10), "Active", "Full-stack developer"));
        employees.put("Mike Johnson", new Employee("EMP003", "Mike Johnson", "Project Manager", "Project Management", 65000.0f, LocalDate.of(2021, 6, 20), "Active", "Agile certified PM"));
        employees.put("Sarah Wilson", new Employee("EMP004", "Sarah Wilson", "Finance Officer", "Finance", 58000.0f, LocalDate.of(2020, 8, 5), "Active", "CPA certified"));
        employees.put("David Brown", new Employee("EMP005", "David Brown", "Tech Support Lead", "Technical Support", 52000.0f, LocalDate.of(2022, 2, 12), "Active", "Technical expert"));
    }

    private void setupRadioButtons() {
        activeRadio.setToggleGroup(statusGroup);
        inactiveRadio.setToggleGroup(statusGroup);
        activeRadio.setSelected(true);
    }

    @FXML
    void onBack(ActionEvent event) {
        switchScene(event, "/com/example/oop_project_group19/HRManagerDashboard.fxml");
    }

    @FXML
    void onCancel(ActionEvent event) {
        clearFields();
        setFieldsEditable(false);
        isEditing = false;
        editRecordBtn.setText("Edit Record");
    }

    @FXML
    void onEditRecord(ActionEvent event) {
        if (!isEditing) {
            if (employeeComboBox.getValue() == null) {
                showAlert("Please select an employee first", Alert.AlertType.WARNING);
                return;
            }
            setFieldsEditable(true);
            isEditing = true;
            editRecordBtn.setText("Cancel Edit");
        } else {
            setFieldsEditable(false);
            isEditing = false;
            editRecordBtn.setText("Edit Record");
        }
    }

    @FXML
    void onEmployeeSelected(ActionEvent event) {
        String selectedEmployee = employeeComboBox.getValue();
        if (selectedEmployee != null && employees.containsKey(selectedEmployee)) {
            Employee emp = employees.get(selectedEmployee);
            loadEmployeeData(emp);
        }
    }

    @FXML
    void onSaveRecord(ActionEvent event) {
        if (!isEditing) {
            showAlert("Please click 'Edit Record' first to modify employee data", Alert.AlertType.WARNING);
            return;
        }

        if (validateInput()) {
            String selectedEmployee = employeeComboBox.getValue();
            Employee emp = employees.get(selectedEmployee);

            emp.setFullName(fullNameField.getText().trim());
            emp.setPosition(positionField.getText().trim());
            emp.setDepartment(departmentComboBox.getValue());
            emp.setSalary(Float.parseFloat(salaryField.getText().trim()));
            emp.setHireDate(hireDatePicker.getValue());
            emp.setStatus(activeRadio.isSelected() ? "Active" : "Inactive");
            emp.setNotes(notesArea.getText().trim());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Updated");
            alert.setHeaderText("Success");
            alert.setContentText("Employee record for " + emp.getFullName() + " has been updated successfully.");
            alert.showAndWait();

            setFieldsEditable(false);
            isEditing = false;
            editRecordBtn.setText("Edit Record");
        }
    }

    private void loadEmployeeData(Employee emp) {
        employeeIdField.setText(emp.getEmployeeId());
        fullNameField.setText(emp.getFullName());
        positionField.setText(emp.getPosition());
        departmentComboBox.setValue(emp.getDepartment());
        salaryField.setText(String.valueOf(emp.getSalary()));
        hireDatePicker.setValue(emp.getHireDate());
        if (emp.getStatus().equals("Active")) {
            activeRadio.setSelected(true);
        } else {
            inactiveRadio.setSelected(true);
        }
        notesArea.setText(emp.getNotes());
    }

    private boolean validateInput() {
        if (fullNameField.getText().trim().isEmpty()) {
            showAlert("Full name is required", Alert.AlertType.ERROR);
            return false;
        }
        if (positionField.getText().trim().isEmpty()) {
            showAlert("Position is required", Alert.AlertType.ERROR);
            return false;
        }
        if (departmentComboBox.getValue() == null) {
            showAlert("Department is required", Alert.AlertType.ERROR);
            return false;
        }
        if (salaryField.getText().trim().isEmpty()) {
            showAlert("Salary is required", Alert.AlertType.ERROR);
            return false;
        }
        try {
            Float.parseFloat(salaryField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid salary amount", Alert.AlertType.ERROR);
            return false;
        }
        if (hireDatePicker.getValue() == null) {
            showAlert("Hire date is required", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void setFieldsEditable(boolean editable) {
        fullNameField.setEditable(editable);
        positionField.setEditable(editable);
        departmentComboBox.setDisable(!editable);
        salaryField.setEditable(editable);
        hireDatePicker.setDisable(!editable);
        activeRadio.setDisable(!editable);
        inactiveRadio.setDisable(!editable);
        notesArea.setEditable(editable);
        saveBtn.setDisable(!editable);
    }

    private void clearFields() {
        employeeComboBox.setValue(null);
        employeeIdField.clear();
        fullNameField.clear();
        positionField.clear();
        departmentComboBox.setValue(null);
        salaryField.clear();
        hireDatePicker.setValue(null);
        activeRadio.setSelected(true);
        notesArea.clear();
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