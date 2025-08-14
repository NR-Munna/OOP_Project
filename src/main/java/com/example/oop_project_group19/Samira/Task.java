package com.example.oop_project_group19.Samira;

import java.time.LocalDate;

public class Task {
    private int taskId;
    private String description;
    private String priority;
    private String assignedTo;
    private LocalDate dueDate;
    private String status;
    private float estimatedHours;
    private String notes;
    private boolean isCritical;

    public Task() {}

    public Task(int taskId, String description, String priority, String assignedTo,
                LocalDate dueDate, float estimatedHours, boolean isCritical) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
        this.estimatedHours = estimatedHours;
        this.isCritical = isCritical;
        this.status = "Not Started";
        this.notes = "";
    }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public float getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(float estimatedHours) { this.estimatedHours = estimatedHours; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isCritical() { return isCritical; }
    public void setCritical(boolean critical) { isCritical = critical; }
}