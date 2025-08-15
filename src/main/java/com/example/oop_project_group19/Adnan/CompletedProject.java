package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class CompletedProject {
    private String projectId;
    private String projectName;
    private String freelancer;
    private String client;
    private LocalDateTime completionDate;

    public CompletedProject(String projectId, String projectName, String freelancer, String client) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.freelancer = freelancer;
        this.client = client;
        this.completionDate = LocalDateTime.now().minusDays((int) (Math.random() * 30));
    }

    public String getProjectId() { return projectId; }
    public String getProjectName() { return projectName; }
    public String getFreelancer() { return freelancer; }
    public String getClient() { return client; }
    public LocalDateTime getCompletionDate() { return completionDate; }
}
