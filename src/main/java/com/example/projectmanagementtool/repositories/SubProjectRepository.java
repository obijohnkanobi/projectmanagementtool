package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.SubProject;
import com.example.projectmanagementtool.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "SELECT * FROM subprojects";
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        return template.query(sql, rowMapper);
    }

    public void addSubProject(SubProject subproject) {
        String sql = "INSERT INTO subprojects (id, name, start_date, end_date, project_id) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, subproject.getId(), subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProject().getId());
    }

    public SubProject findSubProjectById(int id) {
        String sql = "SELECT * FROM subprojects WHERE id = ?";
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        SubProject subproject = template.queryForObject(sql, rowMapper, id);

        String sqlTasks = "SELECT * FROM tasks WHERE subproject_id = ?";
        RowMapper<Task> rowMapperTasks = new BeanPropertyRowMapper<>(Task.class);
        List<Task> tasks = template.query(sqlTasks, rowMapperTasks, id);
        subproject.setTasks(tasks);

        return subproject;
    }

    public Boolean deleteSubProject(int id) {
        String sql = "DELETE FROM subprojects WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateSubProject(int id, SubProject subproject) {
        String sql = "UPDATE subprojects SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE id = ?";
        template.update(sql, subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProject().getId(), subproject.getId());
    }
}
