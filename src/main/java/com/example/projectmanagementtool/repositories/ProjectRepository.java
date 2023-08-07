package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.Project;
import com.example.projectmanagementtool.models.SubProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    @Autowired
    JdbcTemplate template;
    @Autowired
    SubProjectRepository subProjectRepository;

    public List<Project> fetchAll(){
        String sql = "SELECT * FROM projects";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return template.query(sql, rowMapper);
    }

    public void addProject(Project p){
        String sql = "INSERT INTO projects (id, name, start_date, end_date) VALUES (?, ?, ?, ?)";
        template.update(sql, p.getId(), p.getName(), p.getStartDate(), p.getEndDate());
    }

    public Project findProjectById(int id){
        String sql = "SELECT * FROM projects WHERE id = ?";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        Project p = template.queryForObject(sql, rowMapper, id);

        String sqlSubProjects = "SELECT * FROM subprojects WHERE project_id = ?";
        RowMapper<SubProject> rowMapperSubProjects = new BeanPropertyRowMapper<>(SubProject.class);
        List<SubProject> subProjects = template.query(sqlSubProjects, rowMapperSubProjects, id);
        p.setSubProjects(subProjects);
        return p;
    }


    public Boolean deleteProject(int id){
        String sql = "DELETE FROM projects WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateProject(int id, Project p){
        String sql = "UPDATE projects SET name = ?, start_date = ?, end_date = ? WHERE id = ?";
        template.update(sql, p.getName(), p.getStartDate(), p.getEndDate(), p.getId());
    }
    
}
