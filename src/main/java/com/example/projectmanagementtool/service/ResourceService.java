package com.example.projectmanagementtool.service;

import com.example.projectmanagementtool.models.Resource;
import com.example.projectmanagementtool.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    public List<Resource> fetchAll(){
        return resourceRepository.fetchAll();
    }

    public void addResource(Resource resource){
        resourceRepository.addResource(resource);
    }

    public Resource findResourceById(int id){
        return resourceRepository.findResourceById(id);
    }

    public Boolean deleteResource(int id){
        return resourceRepository.deleteResource(id);
    }

    public void updateResource(int id, Resource resource){
        resourceRepository.updateResource(id, resource);
    }
}
