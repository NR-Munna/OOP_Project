package com.example.oop_project_group19.Naimur;

public class ExpenseCategory {
    private String category;
    private float totalAmount;

    public ExpenseCategory(String category, float totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
}