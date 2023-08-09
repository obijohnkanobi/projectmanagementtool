package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.Skill;
import com.example.projectmanagementtool.repositories.SkillRepository;
import com.example.projectmanagementtool.exceptions.ResourceNotFoundException;  // Make sure to import this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public List<Skill> fetchAll(){
        return skillRepository.fetchAll();
    }

    public void addSkill(Skill skill){
        skillRepository.addSkill(skill);
    }

    public Skill findSkillById(int id){
        Skill skill = skillRepository.findSkillById(id);
        if(skill == null) {
            throw new ResourceNotFoundException("Skill with ID " + id + " not found");
        }
        return skill;
    }

    public Boolean deleteSkill(int id){
        // Additional check to see if skill exists before attempting delete
        if(findSkillById(id) == null) {
            throw new ResourceNotFoundException("Skill with ID " + id + " not found");
        }
        return skillRepository.deleteSkill(id);
    }

    public void updateSkill(int id, Skill skill){
        if(findSkillById(id) == null) {
            throw new ResourceNotFoundException("Skill with ID " + id + " not found");
        }
        skillRepository.updateSkill(id, skill);
    }
}
