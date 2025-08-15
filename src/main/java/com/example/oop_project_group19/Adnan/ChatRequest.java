package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class ChatRequest {
    private String requestId;
    private String userId;
    private String username;
    private String priority;
    private LocalDateTime requestTime;
    private String status;

    public ChatRequest(String requestId, String userId, String username, String priority) {
        this.requestId = requestId;
        this.userId = userId;
        this.username = username;
        this.priority = priority;
        this.requestTime = LocalDateTime.now();
        this.status = "Waiting";
    }

    public String getRequestId() { return requestId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPriority() { return priority; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}