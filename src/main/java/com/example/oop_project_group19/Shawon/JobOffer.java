package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class JobOffer {
    private String offerId;
    private String jobTitle;
    private String clientName;
    private double offerAmount;
    private LocalDate deadline;
    private LocalDate receivedDate;
    private String status;
    private String description;

    public JobOffer() {}

    public JobOffer(String offerId, String jobTitle, String clientName, double offerAmount,
                    LocalDate deadline, String description) {
        this.offerId = offerId;
        this.jobTitle = jobTitle;
        this.clientName = clientName;
        this.offerAmount = offerAmount;
        this.deadline = deadline;
        this.description = description;
        this.receivedDate = LocalDate.now();
        this.status = "Pending";
    }

    public String getOfferId() { return offerId; }
    public void setOfferId(String offerId) { this.offerId = offerId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public double getOfferAmount() { return offerAmount; }
    public void setOfferAmount(double offerAmount) { this.offerAmount = offerAmount; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public LocalDate getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDate receivedDate) { this.receivedDate = receivedDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
