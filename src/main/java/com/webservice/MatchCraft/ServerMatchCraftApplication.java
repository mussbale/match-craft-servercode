package com.webservice.MatchCraft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.repo.RoleRepo;

@SpringBootApplication
public class ServerMatchCraftApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerMatchCraftApplication.class, args);
    }
    
	@Bean
	public CommandLineRunner demo(RoleRepo roleRepo) {
	    return (args) -> {
	        // Check if the ROLE_ADMIN already exists
	        String roleName = "ROLE_ADMIN";
	        boolean roleExists = roleRepo.findByName(roleName).isPresent();

	        if (!roleExists) {
	            // Role does not exist, create a new one
	            Role role = new Role();
	            role.setName(roleName);
	            roleRepo.save(role);
	            System.out.println("Created role: " + roleName);
	        } else {
	            // Role already exists, no need to create
	            System.out.println("Role already exists: " + roleName);
	        }
	    };
	}
}