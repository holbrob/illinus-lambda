package com.illinus.vo;

public class StateVo {

	private String stateCd;
	private String stateNm;
	private int statePop;
	
	public StateVo(String newStateCd, String newStateNm, int newStatePop) {
		stateCd = newStateCd;
		stateNm = newStateNm;
		statePop = newStatePop;
	}
	
	
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getStateNm() {
		return stateNm;
	}
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	
	public String getStateDropdownTxt() {
		return stateCd + " - " + stateNm;
	}


	public int getStatePop() {
		return statePop;
	}


	public void setStatePop(int statePop) {
		this.statePop = statePop;
	}
	
	
	@Override
	public String toString() {
		return "StateVo [stateCd=" + stateCd + ", stateNm=" + stateNm + ", statePop=" + statePop + "]";
	}


	
	

}
