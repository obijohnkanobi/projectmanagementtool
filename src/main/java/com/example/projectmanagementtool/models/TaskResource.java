package com.example.projectmanagementtool.models;

public class TaskResource {
    private int id;
    private int taskId;
    private int resourceId;
    private int hoursAllocated;

    public TaskResource() {
    }

    public TaskResource(int id, int taskId, int resourceId, int hoursAllocated) {
        this.id = id;
        this.taskId = taskId;
        this.resourceId = resourceId;
        this.hoursAllocated = hoursAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getHoursAllocated() {
        return hoursAllocated;
    }

    public void setHoursAllocated(int hoursAllocated) {
        this.hoursAllocated = hoursAllocated;
    }
}
