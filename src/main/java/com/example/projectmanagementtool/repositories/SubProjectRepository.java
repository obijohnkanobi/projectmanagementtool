package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.SubProject;
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

    public List<SubProject> fetchAll(){
        String sql = "SELECT * FROM subprojects";
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        return template.query(sql, rowMapper);
    }

    public void addSubProject(SubProject subproject){
        String sql = "INSERT INTO subprojects (id, name, start_date, end_date, project_id) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, subproject.getId(), subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProjectId());
    }

    public SubProject findSubProjectById(int id){
        String sql = "SELECT * FROM subprojects WHERE id = ?";
        RowMapper<SubProject> rowMapper = new BeanPropertyRowMapper<>(SubProject.class);
        SubProject subproject = template.queryForObject(sql, rowMapper, id);
        return subproject;
    }

    public Boolean deleteSubProject(int id){
        String sql = "DELETE FROM subprojects WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateSubProject(int id, SubProject subproject){
        String sql = "UPDATE subprojects SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE id = ?";
        template.update(sql, subproject.getName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getProjectId(), subproject.getId());
    }
}
