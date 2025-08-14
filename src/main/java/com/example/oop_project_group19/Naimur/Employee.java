package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String fullName;
    private String position;
    private String department;
    private float salary;
    private LocalDate hireDate;
    private String status;
    private String notes;

    public Employee(String employeeId, String fullName, String position, String department, float salary, LocalDate hireDate, String status, String notes) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
        this.status = status;
        this.notes = notes;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}