package com.example.oop_project_group19.Shawon;

import java.time.LocalDate;

public class Proposal {
    private String proposalId;
    private String freelancerId;
    private String freelancerName;
    private String jobId;
    private double proposalAmount;
    private String proposalText;
    private int deliveryTime;
    private double freelancerRating;
    private LocalDate submittedDate;

    public Proposal() {}

    public Proposal(String proposalId, String freelancerId, String freelancerName, String jobId,
                    double proposalAmount, String proposalText, int deliveryTime, double freelancerRating) {
        this.proposalId = proposalId;
        this.freelancerId = freelancerId;
        this.freelancerName = freelancerName;
        this.jobId = jobId;
        this.proposalAmount = proposalAmount;
        this.proposalText = proposalText;
        this.deliveryTime = deliveryTime;
        this.freelancerRating = freelancerRating;
        this.submittedDate = LocalDate.now();
    }

    public String getProposalId() { return proposalId; }
    public void setProposalId(String proposalId) { this.proposalId = proposalId; }

    public String getFreelancerId() { return freelancerId; }
    public void setFreelancerId(String freelancerId) { this.freelancerId = freelancerId; }

    public String getFreelancerName() { return freelancerName; }
    public void setFreelancerName(String freelancerName) { this.freelancerName = freelancerName; }

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public double getProposalAmount() { return proposalAmount; }
    public void setProposalAmount(double proposalAmount) { this.proposalAmount = proposalAmount; }

    public String getProposalText() { return proposalText; }
    public void setProposalText(String proposalText) { this.proposalText = proposalText; }

    public int getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }

    public double getFreelancerRating() { return freelancerRating; }
    public void setFreelancerRating(double freelancerRating) { this.freelancerRating = freelancerRating; }

    public LocalDate getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDate submittedDate) { this.submittedDate = submittedDate; }
}