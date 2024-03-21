package com.webservice.MatchCraft.dto;

import java.util.Date;

public class UserResponseDto {
    private Integer id;
	private String name;
    private String username;
    private String email;
    private Long steamId;
    private Date createdAt;
    private Date updatedAt;
    

    public UserResponseDto(Integer id,String name, String username, String email, Long steamId, Date createdAt, Date updatedAt) {
        this.id = id;
    	this.name = name;
        this.username = username;
        this.email = email;
        this.steamId = steamId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        
    }

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getSteamId() {
		return steamId;
	}


	public void setSteamId(Long steamId) {
		this.steamId = steamId;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
