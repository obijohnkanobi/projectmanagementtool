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

    public List<Skill> fetchAll(){
        String sql = "SELECT * FROM skills";
        RowMapper<Skill> rowMapper = new BeanPropertyRowMapper<>(Skill.class);
        return template.query(sql, rowMapper);
    }

    public void addSkill(Skill skill){
        String sql = "INSERT INTO skills (id, name) VALUES (?, ?)";
        template.update(sql, skill.getId(), skill.getName());
    }

    public Skill findSkillById(int id){
        String sql = "SELECT * FROM skills WHERE id = ?";
        RowMapper<Skill> rowMapper = new BeanPropertyRowMapper<>(Skill.class);
        Skill skill = template.queryForObject(sql, rowMapper, id);
        return skill;
    }

    public Boolean deleteSkill(int id){
        String sql = "DELETE FROM skills WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateSkill(int id, Skill skill){
        String sql = "UPDATE skills SET name = ? WHERE id = ?";
        template.update(sql, skill.getName(), skill.getId());
    }
}
