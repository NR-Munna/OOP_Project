package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class LoanRequest {
    private String requestId;
    private String freelancerName;
    private float loanAmount;
    private LocalDate requestDate;
    private String urgency;
    private String status;
    private String reason;

    public LoanRequest(String requestId, String freelancerName, float loanAmount, LocalDate requestDate, String urgency, String status, String reason) {
        this.requestId = requestId;
        this.freelancerName = freelancerName;
        this.loanAmount = loanAmount;
        this.requestDate = requestDate;
        this.urgency = urgency;
        this.status = status;
        this.reason = reason;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getFreelancerName() { return freelancerName; }
    public void setFreelancerName(String freelancerName) { this.freelancerName = freelancerName; }
    public float getLoanAmount() { return loanAmount; }
    public void setLoanAmount(float loanAmount) { this.loanAmount = loanAmount; }
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}