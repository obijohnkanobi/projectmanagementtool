package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.TaskResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskResourceRepository extends JpaRepository<TaskResource, Integer> {
    // Add custom query methods if needed
    List<TaskResource> findByTaskId(int taskId);
    List<TaskResource> findByResourceId(int resourceId);
    List<TaskResource> findByTaskIdAndResourceId(int taskId, int resourceId);
    List<TaskResource> findByHoursAllocatedGreaterThan(int hours);
}
