package com.webservice.MatchCraft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservice.MatchCraft.dto.HeroDto;
import com.webservice.MatchCraft.model.Heroes;
import com.webservice.MatchCraft.repo.HeroesRepo;

@Service
public class HeroesService {

    @Autowired
    private HeroesRepo heroesRepository;


    // Fetch all heroes
    public List<Heroes> findAllHeroes() {
        return heroesRepository.findAll();
    }

    // Find a hero by ID
    public Optional<Heroes> findHeroById(Long id) {
        return heroesRepository.findById(id);
    }

    // Save or update a hero
    public Heroes save(Heroes hero) {
        // Here you can add any logic before saving a hero
        return heroesRepository.save(hero);
    }


    // Delete a hero by ID
    public void deleteHero(Long id) {
        heroesRepository.deleteById(id);
    }
    @Transactional
    public Heroes saveOrUpdateHero(HeroDto heroDTO) {
        Heroes hero;
        
        // Check if there's an existing hero with the given name
        Optional<Heroes> existingHero = heroesRepository.findByName(heroDTO.getName());
        if (existingHero.isPresent()) {
            // If found, update the existing hero
            hero = existingHero.get();
        } else {
            // If not found, create a new hero with the given name
            hero = new Heroes();
        }
        
        // Set or update hero's properties from the DTO
        // Assuming you're updating all properties except the ID
        hero.setName(heroDTO.getName());
        hero.setWinRate(heroDTO.getWinRate());
        hero.setTotalGames(heroDTO.getTotalGames());
        // Add other properties as needed

        // Save the hero entity (Hibernate will perform an insert or update operation as necessary)
        return heroesRepository.save(hero);
    }

}


