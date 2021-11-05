package com.example.plantcare;

public class PlantModal {

    private String plantName;
    private String currentDate;
    private String waterFrequency;
    private String waterAmount;
    private String feedFrequency;
    private String feedAmount;
    private String status;
    private int id;

    public PlantModal(String plantName, String currentDate, String waterFrequency, String waterAmount, String feedFrequency, String feedAmount, String status) {
        this.plantName = plantName;
        this.currentDate = currentDate;
        this.waterFrequency = waterFrequency;
        this.waterAmount = waterAmount;
        this.feedFrequency = feedFrequency;
        this.feedAmount = feedAmount;
        this.status = status;
    }

    // creating getter and setter methods
    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(String waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }

    public String getFeedFrequency() {
        return feedFrequency;
    }

    public void setFeedFrequency(String feedFrequency) {
        this.feedFrequency = feedFrequency;
    }

    public String getFeedAmount() {
        return feedAmount;
    }
    public void setFeedAmount(String feedAmount) {
        this.feedAmount = feedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}




