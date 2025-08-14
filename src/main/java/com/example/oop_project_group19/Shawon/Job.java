package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class Job {
    private String jobId;
    private String title;
    private String description;
    private String clientName;
    private String skills;
    private double budget;
    private LocalDate deadline;
    private LocalDate postedDate;
    private String category;
    private String status;

    public Job() {}

    public Job(String jobId, String title, String description, String clientName,
               String skills, double budget, LocalDate deadline, String category) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.skills = skills;
        this.budget = budget;
        this.deadline = deadline;
        this.category = category;
        this.postedDate = LocalDate.now();
        this.status = "Open";
    }

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public LocalDate getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDate postedDate) { this.postedDate = postedDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return title;
    }
}