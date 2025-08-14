package com.example.oop_project_group19.Naimur;

public class SalaryRecord {
    private String employee;
    private String position;
    private float rate;
    private float hours;
    private float bonus;
    private float deductions;
    private String status;

    public SalaryRecord(String employee, String position, float rate, float hours, float bonus, float deductions, String status) {
        this.employee = employee;
        this.position = position;
        this.rate = rate;
        this.hours = hours;
        this.bonus = bonus;
        this.deductions = deductions;
        this.status = status;
    }

    public String getEmployee() { return employee; }
    public void setEmployee(String employee) { this.employee = employee; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public float getRate() { return rate; }
    public void setRate(float rate) { this.rate = rate; }
    public float getHours() { return hours; }
    public void setHours(float hours) { this.hours = hours; }
    public float getBonus() { return bonus; }
    public void setBonus(float bonus) { this.bonus = bonus; }
    public float getDeductions() { return deductions; }
    public void setDeductions(float deductions) { this.deductions = deductions; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}