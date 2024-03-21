package com.webservice.MatchCraft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.dto.HeroDto;
import com.webservice.MatchCraft.model.Heroes;
import com.webservice.MatchCraft.service.HeroesService;


@RestController
@RequestMapping("/api/heroes")
public class HeroesController {

    @Autowired
    private HeroesService heroesService;
    
    @PostMapping
    public ResponseEntity<?> createOrUpdateHeroes(@RequestBody List<HeroDto> heroDTOs) {
        try {
            List<Heroes> savedHeroes = new ArrayList<>();
            for (HeroDto heroDto : heroDTOs) {
                Heroes savedHero = heroesService.saveOrUpdateHero(heroDto);
                savedHeroes.add(savedHero);
            }
            return ResponseEntity.ok(savedHeroes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create or update heroes: " + e.getMessage());
        }
    }

    
}