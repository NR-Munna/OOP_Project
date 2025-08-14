package com.example.oop_project_group19.Naimur;

public class FinanceFreelancer {
    private String freelancerId;
    private String name;
    private String email;
    private String skills;

    public FinanceFreelancer(String freelancerId, String name, String email, String skills) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public String getFreelancerId() { return freelancerId; }
    public void setFreelancerId(String freelancerId) { this.freelancerId = freelancerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
}