package com.webservice.MatchCraft.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.webservice.MatchCraft.model.Skills;
import com.webservice.MatchCraft.model.User;


public interface SkillsRepo extends CrudRepository<Skills, Long>{
	List<Skills> findAll();
	Skills findByIdIs(Long id);
	List<Skills> findAllByUser(User user);
	@Query("SELECT s FROM Skills s WHERE s.user <> :user")
    List<Skills> findByNotAssociatedWithUser(@Param("user") User user);
	List<Skills> findByUserId(Long userId);
	Optional<Skills> findByUser(User user);

}

