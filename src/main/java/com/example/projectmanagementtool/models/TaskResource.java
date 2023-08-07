package com.example.projectmanagementtool.models;

import javax.persistence.*;

@Entity
@Table(name = "Task_Resources")
public class TaskResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private int hoursAllocated;

    public TaskResource() {
    }

    public TaskResource(int id, Task task, Resource resource, int hoursAllocated) {
        this.id = id;
        this.task = task;
        this.resource = resource;
        this.hoursAllocated = hoursAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getHoursAllocated() {
        return hoursAllocated;
    }

    public void setHoursAllocated(int hoursAllocated) {
        this.hoursAllocated = hoursAllocated;
    }
}
