package com.webservice.MatchCraft.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webservice.MatchCraft.model.Heroes;

@Repository
public interface HeroesRepo extends JpaRepository<Heroes, Long> {
	Heroes findByIdIs(Long id);
	Optional<Heroes> findByName(String name);

}
