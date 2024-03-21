package com.webservice.MatchCraft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    @NotEmpty(message="Username is required!")
	@Size(min=3, max =30, message="Username must be betweeen 3 and 30 characters")
    @Column(nullable = false, unique = true)
    private String userName;
    
    @NotEmpty(message="Email is required!")
	@Email(message="Please enter a valid email!")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "steam_id", nullable = false, unique = true)
    private Long  steamId; // Updated to camelCase

    
    @NotEmpty(message="Password is required!")
	@Size(min = 8, max = 128, message = "Password must be betwen 8 and 128 characters")
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "user")
    private Set<Friendship> friendships;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("user")
    private Skills skills;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
    
    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private Set<Chat> chats = new HashSet<>();
    
    
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
      public String getUserName() {
          return userName;
      }
      public void setUserName(String username) {
          this.userName = username;
      }
      public String getEmail() {
          return email;
      }
      public void setEmail(String email) {
          this.email = email;
      }
      public String getPassword() {
          return password;
      }
      public void setPassword(String password) {
          this.password = password;
      }
      public Set<Role> getRoles() {
          return roles;
      }
      public void setRoles(Set<Role> roles) {
          this.roles = roles;
      }
	  public Long getSteamId() {
		  return steamId;
	  }

    public void setSteamId(Long steamId) {
    	this.steamId = steamId;
    }
	public Set<Friendship> getFriendships() {
		return friendships;
	}
	public void setFriendships(Set<Friendship> friendships) {
		this.friendships = friendships;
	}
	public Set<Chat> getChats() {
		return chats;
	}
	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
	
	public Skills getSkills() {
		return skills;
	}
	public void setSkills(Skills skills) {
		this.skills = skills;
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
