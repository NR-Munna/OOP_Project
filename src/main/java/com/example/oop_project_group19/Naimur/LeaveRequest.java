package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class LeaveRequest {
    private String employee;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days;
    private String reason;

    public LeaveRequest(String employee, String leaveType, LocalDate startDate, LocalDate endDate, int days, String reason) {
        this.employee = employee;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.reason = reason;
    }

    public String getEmployee() { return employee; }
    public void setEmployee(String employee) { this.employee = employee; }
    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}