package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.models.Skill;
import com.example.projectmanagementtool.repositories.SkillRepository;
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
        return skillRepository.findSkillById(id);
    }

    public Boolean deleteSkill(int id){
        return skillRepository.deleteSkill(id);
    }

    public void updateSkill(int id, Skill skill){
        skillRepository.updateSkill(id, skill);
    }
}
