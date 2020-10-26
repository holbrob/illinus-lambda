package com.illinus.util;

import java.util.ArrayList;
import java.util.List;

import com.illinus.vo.StateVo;

public class CovidUtil {
	
	public List<StateVo> buildStateList() {
		List<StateVo> stateList = new ArrayList<StateVo>();
		
		stateList.add(new StateVo("AL", "Alabama", 4903185));		
		stateList.add(new StateVo("AK", "Alaska", 731545));
		stateList.add(new StateVo("AZ", "Arizona", 7278717));
		stateList.add(new StateVo("AR", "Arkansas", 3017804));
		stateList.add(new StateVo("CA", "California", 39512223));
		stateList.add(new StateVo("CO", "Colorado", 5758736));
		stateList.add(new StateVo("CT", "Connecticut", 3565287));
		stateList.add(new StateVo("DC", "District of Columbia", 705749));
		stateList.add(new StateVo("DE", "Delaware", 973764));
		stateList.add(new StateVo("FL", "Florida", 21477737));
		stateList.add(new StateVo("GA", "Georgia", 10617423));
		stateList.add(new StateVo("HI", "Hawaii", 1415872));		
		stateList.add(new StateVo("ID", "Idaho", 1787065));
		stateList.add(new StateVo("IL", "Illinois", 12671821));
		stateList.add(new StateVo("IN", "Indiana", 6732219));
		stateList.add(new StateVo("IA", "Iowa", 3155070));
		stateList.add(new StateVo("KS", "Kansas", 2913314));
		stateList.add(new StateVo("KY", "Kentucky", 4467673));
		stateList.add(new StateVo("LA", "Louisiana", 4648794));
		stateList.add(new StateVo("ME", "Maine", 1344212));
		stateList.add(new StateVo("MD", "Maryland", 6045680));
		stateList.add(new StateVo("MA", "Massachusetts", 6892503));		
		stateList.add(new StateVo("MI", "Michigan", 9986857));
		stateList.add(new StateVo("MN", "Minnesota", 5639632));
		stateList.add(new StateVo("MS", "Mississippi", 2976149));
		stateList.add(new StateVo("MO", "Missouri", 6137428));
		stateList.add(new StateVo("MT", "Montana", 1068778));
		stateList.add(new StateVo("NE", "Nebraska", 1934408));
		stateList.add(new StateVo("NV", "Nevada", 3080156));
		stateList.add(new StateVo("NH", "New Hampshire", 1359711));
		stateList.add(new StateVo("NJ", "New Jersey", 8882190));
		stateList.add(new StateVo("NM", "New Mexico", 2096829));		
		stateList.add(new StateVo("NY", "New York", 19453561));
		stateList.add(new StateVo("NC", "North Carolina", 10488084));
		stateList.add(new StateVo("ND", "North Dakota", 762062));
		stateList.add(new StateVo("OH", "Ohio", 11689100));
		stateList.add(new StateVo("OK", "Oklahoma", 3956971));
		stateList.add(new StateVo("OR", "Oregon", 4217737));
		stateList.add(new StateVo("PA", "Pennsylvania", 12801989));
		stateList.add(new StateVo("RI", "Rhode Island", 1059361));
		stateList.add(new StateVo("SC", "South Carolina", 5148714));
		stateList.add(new StateVo("SD", "South Dakota", 884659));		
		stateList.add(new StateVo("TN", "Tennessee", 6829174));
		stateList.add(new StateVo("TX", "Texas", 28995881));
		stateList.add(new StateVo("UT", "Utah", 3205958));
		stateList.add(new StateVo("VT", "Vermont", 623989));
		stateList.add(new StateVo("VA", "Virginia", 8535519));
		stateList.add(new StateVo("WA", "Washington", 7614893));
		stateList.add(new StateVo("WV", "West Virginia", 1792147));
		stateList.add(new StateVo("WI", "Wisconsin", 5822434));
		stateList.add(new StateVo("WY", "Wyoming", 578759));
		
		stateList.add(new StateVo("PR", "Puerto Rico", 3193614));
		stateList.add(new StateVo("AS", "American Samoa", 55465));
		stateList.add(new StateVo("GU", "Guam", 165768));
		stateList.add(new StateVo("MP", "Northern Marianas", 56882));
		stateList.add(new StateVo("VI", "Virgin Islands", 106977));
		
		return stateList;
	}
	
	public List<String> buildStateDropdown(List<StateVo> stateVoList) {
		List<String> stateDropdownList = new ArrayList<String>();
		
		for (StateVo stateVo : stateVoList) {
			stateDropdownList.add(stateVo.getStateDropdownTxt());
		}
		
		return stateDropdownList;
	}

}
