package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class ProjectPosting {
    private String projectId;
    private String projectTitle;
    private String clientName;
    private LocalDateTime submissionDate;
    private String status;

    public ProjectPosting(String projectId, String projectTitle, String clientName) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.clientName = clientName;
        this.submissionDate = LocalDateTime.now();
        this.status = "Pending Review";
    }

    public String getProjectId() { return projectId; }
    public String getProjectTitle() { return projectTitle; }
    public String getClientName() { return clientName; }
    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}