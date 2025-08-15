package com.example.oop_project_group19.Adnan;

import java.time.LocalDateTime;

class Ticket {
    private String ticketId;
    private String subject;
    private String client;
    private String priority;
    private String status;
    private String category;
    private LocalDateTime creationDate;
    private String assignedTo;

    public Ticket(String ticketId, String subject, String client, String priority, String status, String category) {
        this.ticketId = ticketId;
        this.subject = subject;
        this.client = client;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.creationDate = LocalDateTime.now();
        this.assignedTo = "Unassigned";
    }

    public String getTicketId() { return ticketId; }
    public String getSubject() { return subject; }
    public String getClient() { return client; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public String getCategory() { return category; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public String getAssignedTo() { return assignedTo; }

    public void setStatus(String status) { this.status = status; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}