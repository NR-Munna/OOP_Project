package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class PaymentDelay {
    private String delayId;
    private String clientName;
    private String projectName;
    private float delayedAmount;
    private LocalDate originalDueDate;
    private String delayStatus;

    public PaymentDelay(String delayId, String clientName, String projectName, float delayedAmount, LocalDate originalDueDate, String delayStatus) {
        this.delayId = delayId;
        this.clientName = clientName;
        this.projectName = projectName;
        this.delayedAmount = delayedAmount;
        this.originalDueDate = originalDueDate;
        this.delayStatus = delayStatus;
    }

    public String getDelayId() { return delayId; }
    public void setDelayId(String delayId) { this.delayId = delayId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public float getDelayedAmount() { return delayedAmount; }
    public void setDelayedAmount(float delayedAmount) { this.delayedAmount = delayedAmount; }
    public LocalDate getOriginalDueDate() { return originalDueDate; }
    public void setOriginalDueDate(LocalDate originalDueDate) { this.originalDueDate = originalDueDate; }
    public String getDelayStatus() { return delayStatus; }
    public void setDelayStatus(String delayStatus) { this.delayStatus = delayStatus; }
}