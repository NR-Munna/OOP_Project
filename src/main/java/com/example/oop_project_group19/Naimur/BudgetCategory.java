package com.example.oop_project_group19.Naimur;

public class BudgetCategory {
    private String categoryName;
    private float allocatedAmount;
    private float remainingAmount;

    public BudgetCategory(String categoryName, float allocatedAmount, float remainingAmount) {
        this.categoryName = categoryName;
        this.allocatedAmount = allocatedAmount;
        this.remainingAmount = remainingAmount;
    }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public float getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(float allocatedAmount) { this.allocatedAmount = allocatedAmount; }
    public float getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(float remainingAmount) { this.remainingAmount = remainingAmount; }
}