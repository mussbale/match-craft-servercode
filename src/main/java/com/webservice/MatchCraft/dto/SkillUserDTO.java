package com.webservice.MatchCraft.dto;

public class SkillUserDTO {
	private Long id;
	private String profile_url;
	private String steam_pic;
	private Integer total_wins;
	private Integer total_loss;
	private String favourite_heroes;
	private Integer solo_rank;
	private Integer part_rank;
	private Long userId;
	
	
	public SkillUserDTO(Long id, String profile_url, String steam_pic, Integer total_wins, Integer total_loss, String favourite_heroes, Integer solo_rank, Integer part_rank, Long userId) {
        this.id = id;
        this.profile_url = profile_url;
        this.steam_pic = steam_pic;
        this.total_wins = total_wins;
        this.total_loss = total_loss;
        this.favourite_heroes = favourite_heroes;
        this.solo_rank = solo_rank;
        this.part_rank = part_rank;
        this.userId = userId;
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	public String getSteam_pic() {
		return steam_pic;
	}
	public void setSteam_pic(String steam_pic) {
		this.steam_pic = steam_pic;
	}
	public Integer getTotal_wins() {
		return total_wins;
	}
	public void setTotal_wins(Integer total_wins) {
		this.total_wins = total_wins;
	}
	public Integer getTotal_loss() {
		return total_loss;
	}
	public void setTotal_loss(Integer total_loss) {
		this.total_loss = total_loss;
	}
	public String getFavourite_heroes() {
		return favourite_heroes;
	}
	public void setFavourite_heroes(String favourite_heroes) {
		this.favourite_heroes = favourite_heroes;
	}
	public Integer getSolo_rank() {
		return solo_rank;
	}
	public void setSolo_rank(Integer solo_rank) {
		this.solo_rank = solo_rank;
	}
	public Integer getPart_rank() {
		return part_rank;
	}
	public void setPart_rank(Integer part_rank) {
		this.part_rank = part_rank;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
