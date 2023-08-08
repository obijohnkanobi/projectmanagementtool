package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.exceptions.RepositoryException;
import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubProjectRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    TaskRepository taskRepository;

    public List<SubProject> fetchAll() {
        String sql = "SELECT * FROM subprojects WHERE deleted = FALSE";  // Assuming soft delete flag is named 'deleted'
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        return template.query(sql, rowMapper);
    }

    public void addSubProject(SubProject subproject) {
        String sql = "INSERT INTO subprojects (name, start_date, end_date, project_id) VALUES (?, ?, ?, ?)";
        template.update(sql, subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProjectId());
    }

    public SubProject findSubProjectOnlyById(int id) {
        String sql = "SELECT * FROM subprojects WHERE id = ? AND deleted = FALSE";  // Assuming soft delete flag
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public SubProject findSubProjectWithTasksById(int id) {
        SubProject subproject = findSubProjectOnlyById(id);
        if (subproject != null) {
            List<Task> tasks = taskRepository.findBySubProjectId(id);
            subproject.setTasks(tasks);
        }
        return subproject;
    }

    public Boolean deleteSubProject(int id) {
        // Soft delete instead of direct deletion
        String sql = "UPDATE subprojects SET deleted = TRUE WHERE id = ?";  // Assuming soft delete flag
        return template.update(sql, id) > 0;
    }

    public void updateSubProject(int id, SubProject subproject) {
        String sql = "UPDATE subprojects SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE id = ?";
        template.update(sql, subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProjectId(), subproject.getId());
    }
}
