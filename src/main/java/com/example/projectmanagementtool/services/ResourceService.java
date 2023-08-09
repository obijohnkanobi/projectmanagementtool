package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.exceptions.ResourceNotFoundException;
import com.example.projectmanagementtool.models.Resource;
import com.example.projectmanagementtool.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    // Return only the non-deleted resources
    public List<Resource> fetchAll() {
        return resourceRepository.fetchAllNonDeleted();
    }

    public void addResource(Resource resource) {
        resourceRepository.addResource(resource);
    }

    public Resource findResourceById(int id){
        Resource resource = resourceRepository.findResourceById(id);
        if (resource == null) {
            throw new ResourceNotFoundException("Resource with ID " + id + " not found");
        }
        return resource;
    }


    // Soft delete by marking as deleted
    public Boolean deleteResource(int id) {
        return resourceRepository.softDeleteResource(id);
    }

    public void updateResource(int id, Resource resource) {
        resourceRepository.updateResource(id, resource);
    }
}
