package com.illinus.vo;

public class UsCurrentVo {
	
	private String state;
	private String positive;
	private String positivePerMillion;
	private String hospitalizedCurrently;
	private String hospitalizedCurrentlyPerMillion;
	private String death;
	private String deathPerMillion;
	private String positiveTestRate;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPositive() {
		return positive;
	}
	public void setPositive(String positive) {
		this.positive = positive;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	public String getPositivePerMillion() {
		return positivePerMillion;
	}
	public void setPositivePerMillion(String positivePerMillion) {
		this.positivePerMillion = positivePerMillion;
	}
	public String getDeathPerMillion() {
		return deathPerMillion;
	}
	public void setDeathPerMillion(String deathPerMillion) {
		this.deathPerMillion = deathPerMillion;
	}
	public String getPositiveTestRate() {
		return positiveTestRate;
	}
	public void setPositiveTestRate(String positiveTestRate) {
		this.positiveTestRate = positiveTestRate;
	}	
	public String getHospitalizedCurrently() {
		return hospitalizedCurrently;
	}
	public void setHospitalizedCurrently(String hospitalizedCurrently) {
		this.hospitalizedCurrently = hospitalizedCurrently;
	}
	public String getHospitalizedCurrentlyPerMillion() {
		return hospitalizedCurrentlyPerMillion;
	}
	public void setHospitalizedCurrentlyPerMillion(String hospitalizedCurrentlyPerMillion) {
		this.hospitalizedCurrentlyPerMillion = hospitalizedCurrentlyPerMillion;
	}
	
	
	@Override
	public String toString() {
		return "UsCurrentVo [state=" + state + ", positive=" + positive + ", positivePerMillion=" + positivePerMillion
				+ ", death=" + death + ", deathPerMillion=" + deathPerMillion + ", positiveTestRate=" + positiveTestRate + "]";
	}
	
	
	
	

}
