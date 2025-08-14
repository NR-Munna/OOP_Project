package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class RefundRequest {
    private String requestId;
    private String clientName;
    private String projectName;
    private float refundAmount;
    private LocalDate requestDate;
    private String status;
    private String reason;

    public RefundRequest(String requestId, String clientName, String projectName, float refundAmount, LocalDate requestDate, String status, String reason) {
        this.requestId = requestId;
        this.clientName = clientName;
        this.projectName = projectName;
        this.refundAmount = refundAmount;
        this.requestDate = requestDate;
        this.status = status;
        this.reason = reason;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public float getRefundAmount() { return refundAmount; }
    public void setRefundAmount(float refundAmount) { this.refundAmount = refundAmount; }
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}