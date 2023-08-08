package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskResourceRepository {

    @Autowired
    JdbcTemplate template;

    public List<TaskResource> fetchAll() {
        String sql = "SELECT * FROM task_resources";
        return template.query(sql, new BeanPropertyRowMapper<>(TaskResource.class));
    }

    public void addTaskResource(TaskResource taskResource) {
        String sql = "INSERT INTO task_resources (task_id, resource_id, hours_allocated) VALUES (?, ?, ?)";
        template.update(sql, taskResource.getTaskId(), taskResource.getResourceId(), taskResource.getHoursAllocated());
    }

    public TaskResource findTaskResourceById(int id) {
        String sql = "SELECT * FROM task_resources WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(TaskResource.class), id);
    }

    public Boolean deleteTaskResource(int id) {
        String sql = "DELETE FROM task_resources WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateTaskResource(TaskResource taskResource) {
        String sql = "UPDATE task_resources SET task_id = ?, resource_id = ?, hours_allocated = ? WHERE id = ?";
        template.update(sql, taskResource.getTaskId(), taskResource.getResourceId(), taskResource.getHoursAllocated(), taskResource.getId());
    }
}
