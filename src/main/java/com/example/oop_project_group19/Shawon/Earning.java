package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class Earning {
    private String earningId;
    private String projectName;
    private String clientName;
    private double amount;
    private double fee;
    private LocalDate date;
    private String status;

    public Earning() {}

    public Earning(String earningId, String projectName, String clientName,
                   double amount, double fee, LocalDate date, String status) {
        this.earningId = earningId;
        this.projectName = projectName;
        this.clientName = clientName;
        this.amount = amount;
        this.fee = fee;
        this.date = date;
        this.status = status;
    }

    public String getEarningId() { return earningId; }
    public void setEarningId(String earningId) { this.earningId = earningId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
