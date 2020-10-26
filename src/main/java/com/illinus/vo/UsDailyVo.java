package com.illinus.vo;

public class UsDailyVo {
	
	private String date;
	private String positive;
	private String positiveIncrease;
	private String hospitalizedCurrently;
	private String death;
	private String deathIncrease;
	private String PositiveTestRate;

	
	
	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getPositive() {
		return positive;
	}



	public void setPositive(String positive) {
		this.positive = positive;
	}



	public String getPositiveIncrease() {
		return positiveIncrease;
	}



	public void setPositiveIncrease(String positiveIncrease) {
		this.positiveIncrease = positiveIncrease;
	}



	public String getDeath() {
		return death;
	}



	public String getHospitalizedCurrently() {
		return hospitalizedCurrently;
	}



	public void setHospitalizedCurrently(String hospitalizedCurrently) {
		this.hospitalizedCurrently = hospitalizedCurrently;
	}



	public void setDeath(String death) {
		this.death = death;
	}



	public String getDeathIncrease() {
		return deathIncrease;
	}



	public void setDeathIncrease(String deathIncrease) {
		this.deathIncrease = deathIncrease;
	}
	
	



	public String getPositiveTestRate() {
		return PositiveTestRate;
	}



	public void setPositiveTestRate(String positiveTestRate) {
		PositiveTestRate = positiveTestRate;
	}



	@Override
	public String toString() {
		return "UsDailyVo [date=" + date + ", positive=" + positive + ", positiveIncrease=" + positiveIncrease
				+ ", death=" + death + ", deathIncrease=" + deathIncrease + ", PositiveTestRate=" + PositiveTestRate
				+ ", toString()=" + super.toString() + "]";
	}




	
	
	

}
