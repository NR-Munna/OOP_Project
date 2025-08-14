package com.example.oop_project_group19.Samira;

import java.time.LocalDate;

public class Project {
    private int projectId;
    private String projectName;
    private String client;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private float budget;

    public Project() {}

    public Project(int projectId, String projectName, String client, String status,
                   LocalDate startDate, LocalDate endDate, float budget) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.client = client;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public float getBudget() { return budget; }
    public void setBudget(float budget) { this.budget = budget; }
}