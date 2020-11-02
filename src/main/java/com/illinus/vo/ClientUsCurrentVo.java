package com.illinus.vo;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUsCurrentVo {
	
	@QueryParam("state") private String state;
	@QueryParam("positive") private int positive;
	@QueryParam("hospitalizedCurrently") private int hospitalizedCurrently;
	@QueryParam("death") private int death;
	@QueryParam("totalTestResults") private int totalTestResults;
	
	
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
	

