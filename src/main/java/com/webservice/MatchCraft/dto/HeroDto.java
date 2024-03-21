package com.webservice.MatchCraft.dto;

public class HeroDto {
 
    private String name;
    private Double winRate;
    private Double totalGames;
    public HeroDto(){
    	
    }
    

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getWinRate() {
		return winRate;
	}
	public void setWinRate(Double winRate) {
		this.winRate = winRate;
	}


	public Double getTotalGames() {
		return totalGames;
	}


	public void setTotalGames(Double totalGames) {
		this.totalGames = totalGames;
	}
	
	

}

