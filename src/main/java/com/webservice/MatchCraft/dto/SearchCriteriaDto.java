package com.webservice.MatchCraft.dto;

public class SearchCriteriaDto {
    private Long steamId;
    private String username;

    // No-argument constructor
    public SearchCriteriaDto() {
    }



    // Getters and setters
    public Long getSteamId() {
        return steamId;
    }

    public void setSteamId(Long steamId) {
        this.steamId = steamId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
