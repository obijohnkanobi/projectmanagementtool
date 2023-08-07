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
        return projectRepository.fetchAll();
    }

    public void addProject(Project project){
        projectRepository.addProject(project);
    }

    public Project findProjectById(int id){
        return projectRepository.findProjectById(id);
    }

    public Boolean deleteProject(int id){
        return projectRepository.deleteProject(id);
    }

    public void updateProject(int id, Project project){
        projectRepository.updateProject(id, project);
    }
}
