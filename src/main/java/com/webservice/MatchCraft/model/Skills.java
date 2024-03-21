package com.webservice.MatchCraft.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name= "skills")
public class Skills {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String profile_url;
	private String steam_pic;
	private Integer total_wins;
	private Integer total_loss;
	private String favourite_heroes;
	private Integer solo_rank;
	private Integer part_rank;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Most_Play_heroes",
			joinColumns = @JoinColumn(name = "skills_id"),
			inverseJoinColumns = @JoinColumn(name = "hero_id")
			)
	private List<Heroes> heroes;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;


	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    

    
    public Skills() {}
    
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
	
	public List<Heroes> getHeroes() {
		return heroes;
	}
	public void setHeroes(List<Heroes> heroes) {
		this.heroes = heroes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
	
    
	
	
}
