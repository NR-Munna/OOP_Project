package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class Project {
    private String projectId;
    private String title;
    private String clientId;
    private String freelancerId;
    private String freelancerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private String status;
    private double rating;

    public Project() {}

    public Project(String projectId, String title, String clientId, String freelancerId,
                   String freelancerName, LocalDate startDate, LocalDate endDate, double budget, String status) {
        this.projectId = projectId;
        this.title = title;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
        this.freelancerName = freelancerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = status;
        this.rating = 0.0;
    }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getFreelancerId() { return freelancerId; }
    public void setFreelancerId(String freelancerId) { this.freelancerId = freelancerId; }

    public String getFreelancerName() { return freelancerName; }
    public void setFreelancerName(String freelancerName) { this.freelancerName = freelancerName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return title;
    }
}