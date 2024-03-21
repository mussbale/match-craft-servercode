package com.webservice.MatchCraft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.MatchCraft.dto.SkillUserDTO;
import com.webservice.MatchCraft.model.Heroes;
import com.webservice.MatchCraft.model.Skills;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.SkillsRepo;
import com.webservice.MatchCraft.repo.UserRepo;

@Service
public class SkillsService {

    @Autowired 
    private SkillsRepo repo;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private HeroesService heroesRepository;

    public List<Skills> findAllSkills() {
        return repo.findAll();
    }

    public Optional<Skills> findSkillById(Long id) {
        return repo.findById(id);
    }

    public Skills saveSkill(Skills skill) {
        return repo.save(skill);
    }

    public void deleteSkill(Long id) {
        repo.deleteById(id);
    }

    public List<Skills> findAllByUser(User user) {
        return repo.findAllByUser(user);
    }

    public List<Skills> findByNotAssociatedWithUser(User user) {
        return repo.findByNotAssociatedWithUser(user);
    }
    
    @Cacheable(value = "matchDataCache", key = "#skillUserDTO")
    public Skills saveOrUpdateSkill(SkillUserDTO skillUserDTO) {
        User user = userRepository.findById(skillUserDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + skillUserDTO.getUserId()));

        Optional<Skills> existingSkill = repo.findByUser(user);

        Skills skill = existingSkill.orElse(new Skills()); // Use existing or create new if not found

        // Map SkillUserDTO to Skills entity
        skill.setProfile_url(skillUserDTO.getProfile_url());
        skill.setSteam_pic(skillUserDTO.getSteam_pic());
        skill.setTotal_wins(skillUserDTO.getTotal_wins());
        skill.setTotal_loss(skillUserDTO.getTotal_loss());
        skill.setFavourite_heroes(skillUserDTO.getFavourite_heroes());
        skill.setSolo_rank(skillUserDTO.getSolo_rank());
        skill.setPart_rank(skillUserDTO.getPart_rank());
        skill.setUser(user);

        return repo.save(skill);
    }

    
    @Transactional
    public void addHeroToSkill(Long skillId, Long heroId) {
        Skills skill = repo.findById(skillId)
            .orElseThrow(() -> new RuntimeException("Skill not found"));
        Heroes hero = heroesRepository.findHeroById(heroId)
            .orElseThrow(() -> new RuntimeException("Hero not found"));

        skill.getHeroes().add(hero);
        repo.save(skill);
    }
    public List<Skills> findSkillsByUserId(Long userId) {
        return repo.findByUserId(userId);
    }
 // In SkillsService.java

    public List<SkillUserDTO> findSkillsByUserIdDTO(Long userId) {
        List<Skills> skillsList = findSkillsByUserId(userId);
        List<SkillUserDTO> dtoList = new ArrayList<>();
        for (Skills skill : skillsList) {
            SkillUserDTO dto = new SkillUserDTO(
                skill.getId(),
                skill.getProfile_url(),
                skill.getSteam_pic(),
                skill.getTotal_wins(),
                skill.getTotal_loss(),
                skill.getFavourite_heroes(),
                skill.getSolo_rank(),
                skill.getPart_rank(),
                userId // Assuming you want to include the user's ID directly in the DTO
            );
            dtoList.add(dto);
        }
        return dtoList;
    }



}
