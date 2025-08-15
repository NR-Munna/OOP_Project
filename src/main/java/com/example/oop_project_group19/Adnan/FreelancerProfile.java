package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class FreelancerProfile {
    private String freelancerId;
    private String name;
    private String skills;
    private float rating;
    private String experience;
    private String availability;
    private String email;
    private LocalDateTime lastWarning;

    public FreelancerProfile(String freelancerId, String name, String skills, float rating, String experience, String availability, String email) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.skills = skills;
        this.rating = rating;
        this.experience = experience;
        this.availability = availability;
        this.email = email;
        this.lastWarning = LocalDateTime.now().minusDays((int) (Math.random() * 30));
    }

    public String getFreelancerId() { return freelancerId; }
    public String getName() { return name; }
    public String getSkills() { return skills; }
    public float getRating() { return rating; }
    public String getExperience() { return experience; }
    public String getAvailability() { return availability; }
    public String getEmail() { return email; }
    public LocalDateTime getLastWarning() { return lastWarning; }

    public void setRating(float rating) { this.rating = rating; }
    public void setLastWarning(LocalDateTime lastWarning) { this.lastWarning = lastWarning; }
}