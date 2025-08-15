package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class UserActivity {
    private String userId;
    private String username;
    private String email;
    private int failedAttempts;
    private LocalDateTime lastAttempt;
    private String ipAddress;
    private String status;

    public UserActivity(String userId, String username, String email, int failedAttempts, String ipAddress) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.failedAttempts = failedAttempts;
        this.lastAttempt = LocalDateTime.now();
        this.ipAddress = ipAddress;
        this.status = "Active";
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getFailedAttempts() { return failedAttempts; }
    public LocalDateTime getLastAttempt() { return lastAttempt; }
    public String getIpAddress() { return ipAddress; }
    public String getStatus() { return status; }

    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }
    public void setStatus(String status) { this.status = status; }
}

