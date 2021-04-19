package com.illinus.vo;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CdcUsDailyVo implements Comparable<CdcUsDailyVo> {
	
	@QueryParam("submission_date") private String submission_date;
	@QueryParam("state") private String state;
	@QueryParam("tot_cases") private int tot_cases;
	@QueryParam("new_case") private float new_case;
	@QueryParam("hospitalizedCurrently") private int hospitalizedCurrently;
	@QueryParam("tot_death") private int tot_death;
	@QueryParam("new_death") private float new_death;
	@QueryParam("pnew_case") private int pnew_case;
	

	
	public String getSubmission_date() {
		return submission_date;
	}



	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public int getTot_cases() {
		return tot_cases;
	}



	public void setTot_cases(int tot_cases) {
		this.tot_cases = tot_cases;
	}



	public float getNew_case() {
		return new_case;
	}



	public void setNew_case(float new_case) {
		this.new_case = new_case;
	}



	public int getHospitalizedCurrently() {
		return hospitalizedCurrently;
	}



	public void setHospitalizedCurrently(int hospitalizedCurrently) {
		this.hospitalizedCurrently = hospitalizedCurrently;
	}



	public int getTot_death() {
		return tot_death;
	}



	public void setTot_death(int tot_death) {
		this.tot_death = tot_death;
	}



	public float getNew_death() {
		return new_death;
	}



	public void setNew_death(float new_death) {
		this.new_death = new_death;
	}



	public int getPnew_case() {
		return pnew_case;
	}



	public void setPnew_case(int pnew_case) {
		this.pnew_case = pnew_case;
	}
	
	@Override
    public int compareTo(CdcUsDailyVo o) {
        // usually toString should not be used,
        // instead one of the attributes or more in a comparator chain
        return -1*getSubmission_date().compareTo(o.getSubmission_date());
    }



	@Override
	public String toString() {
		return "CdcUsDailyVo [state=" + state + ", date=" + submission_date + ", positive=" + tot_cases + ", positiveIncrease=" + new_case
				+ ", death=" + tot_death + ", deathIncrease=" + new_death + "]";
	}
	
	
	

	
	

}
