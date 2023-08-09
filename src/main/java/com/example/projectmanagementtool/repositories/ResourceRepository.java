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

    public List<Resource> fetchAllNonDeleted(){
        String sql = "SELECT * FROM resources WHERE deleted = FALSE";
        RowMapper<Resource> rowMapper = new BeanPropertyRowMapper<>(Resource.class);
        return template.query(sql, rowMapper);
    }

    public void addResource(Resource r) {
        String sql = "INSERT INTO resources (name, contact_info, profile_picture, department) VALUES (?, ?, ?, ?)";
        template.update(sql, r.getName(), r.getContactInfo(), r.getProfilePicture(), r.getDepartment());
    }

    public Resource findResourceById(int id) {
        String sql = "SELECT * FROM resources WHERE id = ?";
        RowMapper<Resource> rowMapper = new BeanPropertyRowMapper<>(Resource.class);
        Resource r = template.queryForObject(sql, rowMapper, id);
        return r;
    }

    public Boolean softDeleteResource(int id){
        String sql = "UPDATE resources SET deleted = TRUE WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateResource(int id, Resource r){
        String sql = "UPDATE resources SET name = ?, contact_info = ?, profile_picture = ?, department = ? WHERE id = ?";
        template.update(sql, r.getName(), r.getContactInfo(), r.getProfilePicture(), r.getDepartment(), id);
    }
}
