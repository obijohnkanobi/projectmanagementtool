package com.example.projectmanagementtool.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks") // Maps to the "tasks" table in the database
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Date startDate;
    private Date endDate;
    private boolean isPending;
    private int estimatedHours; // Adding the estimatedHours property

    @ManyToOne
    @JoinColumn(name = "fk_subproject_id") // This represents the foreign key in the "tasks" table
    private SubProject subProject;

    // Constructors, getters, and setters...

    public Task() {
    }

    public Task(int id, String name, Date startDate, Date endDate, boolean isPending, int estimatedHours) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPending = isPending;
        this.estimatedHours = estimatedHours;
    }

    // Getters and setters for properties...

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

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
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
