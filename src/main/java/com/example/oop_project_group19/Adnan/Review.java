package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class Review {
    private String reviewId;
    private String userId;
    private String username;
    private String type;
    private float rating;
    private String content;
    private LocalDateTime timestamp;
    private String status;
    private String priority;

    public Review(String reviewId, String userId, String username, String type, float rating, String content, String priority) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.username = username;
        this.type = type;
        this.rating = rating;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.status = "New";
        this.priority = priority;
    }

    public String getReviewId() { return reviewId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getType() { return type; }
    public float getRating() { return rating; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }

    public void setStatus(String status) { this.status = status; }
}