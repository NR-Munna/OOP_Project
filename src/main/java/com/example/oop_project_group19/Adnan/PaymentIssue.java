package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class PaymentIssue {
    private String issueId;
    private String userId;
    private String username;
    private String transactionId;
    private float amount;
    private String issueType;
    private String description;
    private LocalDateTime timestamp;
    private String status;

    public PaymentIssue(String issueId, String userId, String username, String transactionId, float amount, String issueType, String description) {
        this.issueId = issueId;
        this.userId = userId;
        this.username = username;
        this.transactionId = transactionId;
        this.amount = amount;
        this.issueType = issueType;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.status = "Pending";
    }

    public String getIssueId() { return issueId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getTransactionId() { return transactionId; }
    public float getAmount() { return amount; }
    public String getIssueType() { return issueType; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
