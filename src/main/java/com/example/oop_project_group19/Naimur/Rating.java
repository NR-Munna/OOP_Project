package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Rating {
    private String freelancer;
    private String client;
    private String project;
    private float rating;
    private String comment;
    private LocalDate date;

    public Rating(String freelancer, String client, String project, float rating, String comment, LocalDate date) {
        this.freelancer = freelancer;
        this.client = client;
        this.project = project;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getFreelancer() { return freelancer; }
    public void setFreelancer(String freelancer) { this.freelancer = freelancer; }
    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }
    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}