package com.example.projectmanagementtool.models;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int estimatedHours;
    private int subprojectId;

    public Task() {
    }

    public Task(int id, String name, Date startDate, Date endDate, int estimatedHours, int subprojectId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedHours = estimatedHours;
        this.subprojectId = subprojectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }
}
