package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.repositories.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {
    @Autowired
    SubProjectRepository subProjectRepository;

    public List<SubProject> fetchAll(){
        return subProjectRepository.fetchAll();
    }

    public void addSubProject(SubProject subProject){
        subProjectRepository.addSubProject(subProject);
    }

    public SubProject findSubProjectById(int id){
        return subProjectRepository.findSubProjectById(id);
    }

    public Boolean deleteSubProject(int id){
        return subProjectRepository.deleteSubProject(id);
    }

    public void updateSubProject(int id, SubProject subProject){
        subProjectRepository.updateSubProject(id, subProject);
    }
}
