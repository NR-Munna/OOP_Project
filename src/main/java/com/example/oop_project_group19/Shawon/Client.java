package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class Client {
    private String clientId;
    private String name;
    private String email;
    private String company;
    private double totalSpent;
    private int completedProjects;
    private double rating;

    public Client() {}

    public Client(String clientId, String name, String email, String company) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.company = company;
        this.totalSpent = 0.0;
        this.completedProjects = 0;
        this.rating = 0.0;
    }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }

    public int getCompletedProjects() { return completedProjects; }
    public void setCompletedProjects(int completedProjects) { this.completedProjects = completedProjects; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}