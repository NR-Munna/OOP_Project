package com.example.oop_project_group19.Samira;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String type;
    private String status;
    private LocalDate joinDate;
    private String email;
    private LocalDate lastActive;
    private int projectCount;

    public User() {}

    public User(int id, String name, String type, String status, LocalDate joinDate, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.joinDate = joinDate;
        this.email = email;
        this.lastActive = LocalDate.now();
        this.projectCount = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getLastActive() { return lastActive; }
    public void setLastActive(LocalDate lastActive) { this.lastActive = lastActive; }

    public int getProjectCount() { return projectCount; }
    public void setProjectCount(int projectCount) { this.projectCount = projectCount; }
}