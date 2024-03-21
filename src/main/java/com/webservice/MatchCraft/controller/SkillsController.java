package com.webservice.MatchCraft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.dto.SkillUserDTO;
import com.webservice.MatchCraft.model.Skills;
import com.webservice.MatchCraft.service.SkillsService;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @GetMapping
    public List<Skills> getAllSkills() {
        return skillsService.findAllSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable Long id) {
        return skillsService.findSkillById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillUserDTO>> getSkillsByUserId(@PathVariable Long userId) {
        List<SkillUserDTO> skillsDTOs = skillsService.findSkillsByUserIdDTO(userId);
        if (skillsDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skillsDTOs);
    }
    @PostMapping
    public ResponseEntity<Skills> createOrUpdateSkill(@RequestBody SkillUserDTO skillUserDTO) {
        Skills updatedSkill = skillsService.saveOrUpdateSkill(skillUserDTO);
        return ResponseEntity.ok(updatedSkill);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable Long id, @RequestBody Skills skillDetails) {
        return skillsService.findSkillById(id)
                .map(skill -> {
                    skill.setProfile_url(skillDetails.getProfile_url());
                    // Update other fields as necessary
                    Skills updatedSkill = skillsService.saveSkill(skill);
                    return ResponseEntity.ok(updatedSkill);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        return skillsService.findSkillById(id)
                .map(skill -> {
                    skillsService.deleteSkill(skill.getId());
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{skillId}/heroes/{heroId}")
    public void addHeroToSkill(@PathVariable Long skillId, @PathVariable Long heroId) {
        skillsService.addHeroToSkill(skillId, heroId);
    }
}
