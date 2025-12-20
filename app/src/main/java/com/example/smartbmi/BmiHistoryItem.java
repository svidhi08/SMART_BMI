package com.example.smartbmi;

public class BmiHistoryItem {
    private String date;
    private String bmiValue;
    private String category;

    public BmiHistoryItem(String date, String bmiValue, String category) {
        this.date = date;
        this.bmiValue = bmiValue;
        this.category = category;
    }

    public String getDisplayString() {
        return date + " -> " + bmiValue + " (" + category + ")";
    }
}