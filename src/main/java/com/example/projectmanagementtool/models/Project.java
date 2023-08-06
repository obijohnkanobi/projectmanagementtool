package com.example.projectmanagementtool.models;

import java.util.Date;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<SubProject> subProjects;

    // Constructors, getters, and setters

    public Project() {
    }

    public Project(int id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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


    public List<SubProject> getSubProjects() {
        return subProjects;
    }
    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
