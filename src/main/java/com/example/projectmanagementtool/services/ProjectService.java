package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.Project;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public List<Project> fetchAll(){
        try {
            return projectRepository.fetchAll();
        } catch (Exception e) {
            // Log the exception
            // e.printStackTrace();  // for demonstration purposes
            throw new RuntimeException("Error fetching all projects.", e);
        }
    }

    public void addProject(Project project){
        try {
            projectRepository.addProject(project);
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error adding the project.", e);
        }
    }

    public Project findProjectById(int id){
        try {
            return projectRepository.findProjectById(id);
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error finding the project by ID: " + id, e);
        }
    }

    public Boolean deleteProject(int id){
        try {
            return projectRepository.deleteProject(id);
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error deleting the project with ID: " + id, e);
        }
    }

    public void updateProject(int id, Project project){
        try {
            projectRepository.updateProject(id, project);
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error updating the project with ID: " + id, e);
        }
    }
}
