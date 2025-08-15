package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class PasswordResetRequest {
    private String requestId;
    private String userId;
    private String username;
    private String email;
    private String userType;
    private LocalDateTime requestTime;
    private String status;

    public PasswordResetRequest(String requestId, String userId, String username, String email, String userType) {
        this.requestId = requestId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.requestTime = LocalDateTime.now();
        this.status = "Pending";
    }

    public String getRequestId() { return requestId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getUserType() { return userType; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}