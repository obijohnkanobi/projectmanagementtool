package com.example.projectmanagementtool.models;

import com.example.projectmanagementtool.models.Task;


import java.util.Date;
import java.util.List;
/**
 * Represents a sub-project in the project management tool.
 */
public class SubProject {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int projectId;
    private boolean deleted;  // Assuming soft delete flag is named 'deleted'
    private List<Task> tasks; // List of tasks, but fetching this would be manual with JDBC

    public SubProject() {}
    public SubProject(int id, String name, Date startDate, Date endDate, int projectId, boolean deleted, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.deleted = deleted;
        this.tasks = tasks;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}
