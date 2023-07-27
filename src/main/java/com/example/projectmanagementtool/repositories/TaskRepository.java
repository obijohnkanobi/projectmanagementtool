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
        String sql = "SELECT * FROM Tasks";
        return template.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }

    public void addTask(Task task){
        String sql = "INSERT INTO Tasks (name, start_date, end_date, estimated_hours, subproject_id) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, task.getName(), task.getStartDate(), task.getEndDate(), task.getEstimatedHours(), task.getSubprojectId());
    }

    public Task findTaskById(int id){
        String sql = "SELECT * FROM Tasks WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Task.class), id);
    }

    public Boolean deleteTask(int id){
        String sql = "DELETE FROM Tasks WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateTask(int id, Task task){
        String sql = "UPDATE Tasks SET name = ?, start_date = ?, end_date = ?, estimated_hours = ?, subproject_id = ? WHERE id = ?";
        template.update(sql, task.getName(), task.getStartDate(), task.getEndDate(), task.getEstimatedHours(), task.getSubprojectId(), id);
    }
}
