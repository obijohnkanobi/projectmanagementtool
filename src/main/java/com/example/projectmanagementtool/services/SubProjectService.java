package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.exceptions.RepositoryException;
import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.repositories.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubProjectService {
    @Autowired
    SubProjectRepository subProjectRepository;

    public List<SubProject> fetchAll(){
        return subProjectRepository.fetchAll();
    }

    public void addSubProject(SubProject subProject) {
        try {
            subProjectRepository.addSubProject(subProject);
        } catch (Exception e) {
            System.out.println("Error adding subproject: " + e.getMessage());
            // Perhaps you might want to rethrow the exception or handle it differently
        }
    }

    public SubProject findSubProjectWithTasksById(int id) {
        try {
            return subProjectRepository.findSubProjectWithTasksById(id);
        } catch (RepositoryException re) {
            System.out.println("Error: " + re.getMessage());
            return null;
        } catch (DataAccessException dae) {
            System.out.println("Error: " + dae.getMessage());
            return null;
        }
    }

    public Boolean deleteSubProject(int id) {
        try {
            return subProjectRepository.deleteSubProject(id);
        } catch (RepositoryException re) {
            System.out.println("Error: " + re.getMessage());
            return false;
        } catch (DataAccessException dae) {
            System.out.println("Error: " + dae.getMessage());
            return false;
        }
    }

    public void updateSubProject(int id, SubProject subProject) {
        try {
            subProjectRepository.updateSubProject(id, subProject);
        } catch (Exception e) {
            System.out.println("Error updating subproject: " + e.getMessage());
            // Handle the exception accordingly
        }
    }

}
