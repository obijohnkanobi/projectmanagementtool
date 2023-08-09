package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillRepository {
    @Autowired
    JdbcTemplate template;

    // Fetch only non-deleted skills
    public List<Skill> fetchAll(){
        String sql = "SELECT * FROM skills WHERE deleted = FALSE";
        RowMapper<Skill> rowMapper = new BeanPropertyRowMapper<>(Skill.class);
        return template.query(sql, rowMapper);
    }

    public void addSkill(Skill skill){
        String sql = "INSERT INTO skills (name) VALUES (?)";  // Removed id as it's auto-generated
        template.update(sql, skill.getName());
    }

    public Skill findSkillById(int id){
        String sql = "SELECT * FROM skills WHERE id = ? AND deleted = FALSE";
        RowMapper<Skill> rowMapper = new BeanPropertyRowMapper<>(Skill.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    // Implement soft delete
    public Boolean deleteSkill(int id){
        String sql = "UPDATE skills SET deleted = TRUE WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateSkill(int id, Skill skill){
        String sql = "UPDATE skills SET name = ? WHERE id = ?";
        template.update(sql, skill.getName(), id);  // Changed from skill.getId() to id
    }
}

