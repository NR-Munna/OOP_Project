package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Freelancer {
    private String name;
    private String email;
    private String skills;
    private float rating;
    private String status;
    private boolean interviewed;
    private LocalDate joinDate;

    public Freelancer(String name, String email, String skills, float rating, String status, boolean interviewed, LocalDate joinDate) {
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.rating = rating;
        this.status = status;
        this.interviewed = interviewed;
        this.joinDate = joinDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isInterviewed() { return interviewed; }
    public void setInterviewed(boolean interviewed) { this.interviewed = interviewed; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
}