package com.illinus.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUsDailyVo {
	
	private String date;
	private String state;
	private int positive;
	private int positiveIncrease;
	private int hospitalizedCurrently;
	private int death;
	private int deathIncrease;
	private int totalTestResultsIncrease;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
	public int getPositiveIncrease() {
		return positiveIncrease;
	}
	public void setPositiveIncrease(int positiveIncrease) {
		this.positiveIncrease = positiveIncrease;
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
	public int getDeathIncrease() {
		return deathIncrease;
	}
	public void setDeathIncrease(int deathIncrease) {
		this.deathIncrease = deathIncrease;
	}
	public int getTotalTestResultsIncrease() {
		return totalTestResultsIncrease;
	}
	public void setTotalTestResultsIncrease(int totalTestResultsIncrease) {
		this.totalTestResultsIncrease = totalTestResultsIncrease;
	}
	
	@Override
	public String toString() {
		return "ClientUsDailyVo [date=" + date + ", positive=" + positive + ", positiveIncrease=" + positiveIncrease
				+ ", death=" + death + ", deathIncrease=" + deathIncrease + "]";
	}
	
	
	

	
	

}
