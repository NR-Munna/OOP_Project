package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Training {
    private String freelancer;
    private String course;
    private LocalDate startDate;
    private float progress;
    private String status;

    public Training(String freelancer, String course, LocalDate startDate, float progress, String status) {
        this.freelancer = freelancer;
        this.course = course;
        this.startDate = startDate;
        this.progress = progress;
        this.status = status;
    }

    public String getFreelancer() { return freelancer; }
    public void setFreelancer(String freelancer) { this.freelancer = freelancer; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public float getProgress() { return progress; }
    public void setProgress(float progress) { this.progress = progress; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}