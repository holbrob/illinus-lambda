package com.illinus.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUsCurrentVo {
	
	private String state;
	private int positive;
	private int hospitalizedCurrently;
	private int death;
	private int totalTestResults;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getHospitalizedCurrently() {
		return hospitalizedCurrently;
	}
	public void setHospitalizedCurrently(int hospitalizedCurrently) {
		this.hospitalizedCurrently = hospitalizedCurrently;
	}
	public int getDeath() {
		return death;
	}
	public void setDeath(int death) {
		this.death = death;
	}	
	public int getTotalTestResults() {
		return totalTestResults;
	}
	public void setTotalTestResults(int totalTestResults) {
		this.totalTestResults = totalTestResults;
	}
	@Override
	public String toString() {
		return "ClientUsCurrentVo [state=" + state + ", positive=" + positive + ", death=" + death
				+ ", totalTestResults=" + totalTestResults + "]";
	}
	
	
}
	

