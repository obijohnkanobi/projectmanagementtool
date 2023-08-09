package com.example.projectmanagementtool.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String contactInfo; // New field
    private String profilePicture; // New field
    private String department; // New field
    private boolean deleted; // New field

    // Standard no-argument constructor
    public Resource() {}

    public Resource(int id, String name, String contactInfo, String profilePicture, String department, boolean deleted) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.profilePicture = profilePicture;
        this.department = department;
        this.deleted = deleted;
    }
    // Getters and setters for all fields...

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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    // Add getters and setters for the new fields
}
