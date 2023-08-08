package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate template;

    public List<Task> fetchAll(){
        String sql = "SELECT * FROM tasks WHERE deleted = FALSE"; // Only fetch non-deleted tasks
        return template.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }

    public void addTask(Task task){
        String sql = "INSERT INTO tasks (name, start_date, end_date, is_pending_approval, estimated_hours, fk_subproject_id) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, task.getName(), task.getStartDate(), task.getEndDate(), task.isPendingApproval(), task.getEstimatedHours(), task.getSubProject().getId()); // Assuming the Task model has a getSubProjectId() method instead of getSubProject().getId()
    }

    public Task findTaskById(int id){
        String sql = "SELECT * FROM tasks WHERE id = ? AND deleted = FALSE"; // Only fetch non-deleted task
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Task.class), id);
    }

    public Boolean deleteTask(int id){
        // Soft delete instead of actual delete
        String sql = "UPDATE tasks SET deleted = TRUE WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateTask(int id, Task task){
        String sql = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, is_pending_approval = ?, estimated_hours = ?, fk_subproject_id = ? WHERE id = ?";
        template.update(sql, task.getName(), task.getStartDate(), task.getEndDate(), task.isPendingApproval(), task.getEstimatedHours(), task.getSubProject().getId(), id); // Same assumption about getSubProjectId()
    }
    public List<Task> findBySubProjectId(int subProjectId) {
        String sql = "SELECT * FROM tasks WHERE fk_subproject_id = ? AND deleted = FALSE";
        return template.query(sql, new BeanPropertyRowMapper<>(Task.class), subProjectId);
    }

}
