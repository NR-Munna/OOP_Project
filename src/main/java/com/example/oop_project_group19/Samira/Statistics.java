package com.example.oop_project_group19.Samira;

public class Statistics {
    private String statisticName;
    private String value;
    private String percentage;

    public Statistics() {}

    public Statistics(String statisticName, String value, String percentage) {
        this.statisticName = statisticName;
        this.value = value;
        this.percentage = percentage;
    }

    public String getStatisticName() { return statisticName; }
    public void setStatisticName(String statisticName) { this.statisticName = statisticName; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getPercentage() { return percentage; }
    public void setPercentage(String percentage) { this.percentage = percentage; }
}