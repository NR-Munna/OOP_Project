package com.example.oop_project_group19.Samira;

import java.time.LocalDate;

public class Freelancer {
    private int freelancerId;
    private String name;
    private String skills;
    private float hourlyRate;
    private float rating;
    private boolean isAvailable;
    private int completedProjects;

    public Freelancer() {}

    public Freelancer(int freelancerId, String name, String skills, float hourlyRate, float rating, boolean isAvailable) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.skills = skills;
        this.hourlyRate = hourlyRate;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.completedProjects = 0;
    }

    public int getFreelancerId() { return freelancerId; }
    public void setFreelancerId(int freelancerId) { this.freelancerId = freelancerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public float getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(float hourlyRate) { this.hourlyRate = hourlyRate; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public int getCompletedProjects() { return completedProjects; }
    public void setCompletedProjects(int completedProjects) { this.completedProjects = completedProjects; }
}