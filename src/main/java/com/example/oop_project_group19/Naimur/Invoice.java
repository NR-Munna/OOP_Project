package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Invoice {
    private String invoiceId;
    private String clientName;
    private float amount;
    private LocalDate dueDate;
    private String status;

    public Invoice(String invoiceId, String clientName, float amount, LocalDate dueDate, String status) {
        this.invoiceId = invoiceId;
        this.clientName = clientName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}