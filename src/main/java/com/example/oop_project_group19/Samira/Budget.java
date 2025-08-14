package com.example.oop_project_group19.Samira;

public class Budget {
    private int budgetId;
    private String projectName;
    private float totalBudget;
    private float allocated;
    private float spent;
    private float remaining;
    private String status;
    private float developmentCost;
    private float designCost;
    private float testingCost;
    private float miscCost;
    private float contingency;

    public Budget() {}

    public Budget(int budgetId, String projectName, float totalBudget) {
        this.budgetId = budgetId;
        this.projectName = projectName;
        this.totalBudget = totalBudget;
        this.allocated = 0;
        this.spent = 0;
        this.remaining = totalBudget;
        this.status = "Active";
        this.developmentCost = 0;
        this.designCost = 0;
        this.testingCost = 0;
        this.miscCost = 0;
        this.contingency = 0;
    }

    public int getBudgetId() { return budgetId; }
    public void setBudgetId(int budgetId) { this.budgetId = budgetId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public float getTotalBudget() { return totalBudget; }
    public void setTotalBudget(float totalBudget) { this.totalBudget = totalBudget; }

    public float getAllocated() { return allocated; }
    public void setAllocated(float allocated) { this.allocated = allocated; }

    public float getSpent() { return spent; }
    public void setSpent(float spent) { this.spent = spent; }

    public float getRemaining() { return remaining; }
    public void setRemaining(float remaining) { this.remaining = remaining; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public float getDevelopmentCost() { return developmentCost; }
    public void setDevelopmentCost(float developmentCost) { this.developmentCost = developmentCost; }

    public float getDesignCost() { return designCost; }
    public void setDesignCost(float designCost) { this.designCost = designCost; }

    public float getTestingCost() { return testingCost; }
    public void setTestingCost(float testingCost) { this.testingCost = testingCost; }

    public float getMiscCost() { return miscCost; }
    public void setMiscCost(float miscCost) { this.miscCost = miscCost; }

    public float getContingency() { return contingency; }
    public void setContingency(float contingency) { this.contingency = contingency; }
}