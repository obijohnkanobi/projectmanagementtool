package com.example.projectmanagementtool.models;

import com.example.projectmanagementtool.models.SubProject;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isPendingApproval; // Renamed field
    private int estimatedHours;
    private SubProject subProject;

    public Task(int id, String name, LocalDateTime startDate, LocalDateTime endDate, boolean isPendingApproval, int estimatedHours, SubProject subProject) { // Renamed parameter
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPendingApproval = isPendingApproval; // Updated assignment
        this.estimatedHours = estimatedHours;
        this.subProject = subProject;
    }

    public Task() {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isPendingApproval() {
        return isPendingApproval;
    }

    public void setPendingApproval(boolean pendingApproval) {
        isPendingApproval = pendingApproval;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }
}