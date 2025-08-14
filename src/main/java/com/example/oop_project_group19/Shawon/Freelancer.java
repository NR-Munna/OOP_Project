package com.example.oop_project_group19.Shawon;

import java.util.List;
import java.util.ArrayList;

public class Freelancer {
    private String freelancerId;
    private String name;
    private String email;
    private String skills;
    private double hourlyRate;
    private double rating;
    private int completedJobs;
    private String profilePicture;
    private List<String> portfolio;
    private boolean isAvailable;

    public Freelancer() {
        this.portfolio = new ArrayList<>();
        this.isAvailable = true;
    }

    public Freelancer(String freelancerId, String name, String email, String skills, double hourlyRate) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.hourlyRate = hourlyRate;
        this.rating = 0.0;
        this.completedJobs = 0;
        this.portfolio = new ArrayList<>();
        this.isAvailable = true;
    }

    public String getFreelancerId() { return freelancerId; }
    public void setFreelancerId(String freelancerId) { this.freelancerId = freelancerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(int completedJobs) { this.completedJobs = completedJobs; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public List<String> getPortfolio() { return portfolio; }
    public void setPortfolio(List<String> portfolio) { this.portfolio = portfolio; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}