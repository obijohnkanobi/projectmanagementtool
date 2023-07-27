package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.models.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceRepository {
    @Autowired
    JdbcTemplate template;

    public List<Resource> fetchAll(){
        String sql = "SELECT * FROM resources";
        RowMapper<Resource> rowMapper = new BeanPropertyRowMapper<>(Resource.class);
        return template.query(sql, rowMapper);
    }

    public void addResource(Resource r){
        String sql = "INSERT INTO resources (id, name) VALUES (?, ?)";
        template.update(sql, r.getId(), r.getName());
    }

    public Resource findResourceById(int id){
        String sql = "SELECT * FROM resources WHERE id = ?";
        RowMapper<Resource> rowMapper = new BeanPropertyRowMapper<>(Resource.class);
        Resource r = template.queryForObject(sql, rowMapper, id);
        return r;
    }

    public Boolean deleteResource(int id){
        String sql = "DELETE FROM resources WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateResource(int id, Resource r){
        String sql = "UPDATE resources SET name = ? WHERE id = ?";
        template.update(sql, r.getName(), r.getId());
    }
}
