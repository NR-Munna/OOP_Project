package com.example.oop_project_group19.Naimur;

import java.time.LocalDate;

public class Expense {
    private String category;
    private float amount;
    private LocalDate date;
    private String description;

    public Expense(String category, float amount, LocalDate date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}