package com.illinus.service;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.illinus.util.CovidUtil;
import com.illinus.vo.ClientUsCurrentVo;
import com.illinus.vo.ClientUsDailyVo;
import com.illinus.vo.ParmsVo;
import com.illinus.vo.StateVo;
import com.illinus.vo.UsCurrentVo;
import com.illinus.vo.UsDailyVo;


public class CovidService {
	
    private static NumberFormat numberFormat = NumberFormat.getInstance();
    private static NumberFormat doubleFormat = NumberFormat.getInstance();
    private static NumberFormat percentFormat = NumberFormat.getInstance();
    
    static {
        numberFormat.setGroupingUsed(true);
        
        doubleFormat.setMaximumFractionDigits(0);
        doubleFormat.setRoundingMode(RoundingMode.CEILING);
        doubleFormat.setGroupingUsed(true);
        
        percentFormat.setMaximumFractionDigits(2);
        percentFormat.setGroupingUsed(true);
    	
    }
	
	CovidUtil covidUtil = new CovidUtil();
	
	/**
	 * @return
	 */
	public List<String> getStates() {
		List<StateVo> stateList = covidUtil.buildStateList();
		return covidUtil.buildStateDropdown(stateList);
		
	}
	
	/**
	 * @return
	 */
	public List<UsDailyVo> getUsDaily(ParmsVo parmsVo) {
		List<UsDailyVo> usDailyVoList = new ArrayList<UsDailyVo>();
		
	    String targetUrl;
	    if (parmsVo.getStateCd()==null) {
	    	targetUrl = "https://covidtracking.com/api/v1/us/daily.json";
	    } else {
	    	targetUrl = "https://covidtracking.com/api/v1/states/" + parmsVo.getStateCd().toLowerCase() + "/daily.json";
	    }
	    SimpleDateFormat sdfIn = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat sdfOut = new SimpleDateFormat("MMM dd");
	    
	    Client client = ClientBuilder.newClient();
	    Response response = client.target(targetUrl).request().get();
	    ClientUsDailyVo[] clientUsDailyVoList = response.readEntity(ClientUsDailyVo[].class);

	    response.close();
	    client.close();
	    
	    for (int i =0; i < clientUsDailyVoList.length; i++) {
	    	if (i % parmsVo.getOutputDayQty() != 0) {
	    		continue;
	    	}
	    	
	    	UsDailyVo usDailyVo = new UsDailyVo();
	    	try {
		    	Date d = sdfIn.parse(clientUsDailyVoList[i].getDate());
		    	usDailyVo.setDate(sdfOut.format(d));	    		
	    	} catch (ParseException e) {
	    		usDailyVo.setDate(clientUsDailyVoList[i].getDate());
	    	}
	    	int positive = 0;
	    	int positiveIncrease = 0;
	    	int death = 0;
	    	int deathIncrease = 0;
	    	int totalTestResultsIncrease = 0;

	    	for (int j = i; j < i + parmsVo.getTotalRollingAvgDayQty(); j++) {
	    		if (j < clientUsDailyVoList.length ) {
		    		positive += clientUsDailyVoList[j].getPositive();
		    		death += clientUsDailyVoList[j].getDeath();	    			
	    		}
	    	}
	    	
	    	for (int j = i; j < i + parmsVo.getChangeRollingAvgDayQty(); j++) {
	    		if (j < clientUsDailyVoList.length ) {
		    		positiveIncrease += clientUsDailyVoList[j].getPositiveIncrease();
		    		deathIncrease += clientUsDailyVoList[j].getDeathIncrease();
		    		totalTestResultsIncrease += clientUsDailyVoList[j].getTotalTestResultsIncrease();	    			
	    		}
	    	}
	    	
	    	usDailyVo.setPositive(numberFormat.format((int) positive/parmsVo.getTotalRollingAvgDayQty()));
	    	usDailyVo.setPositiveIncrease(numberFormat.format((int) positiveIncrease/(parmsVo.getChangeRollingAvgDayQty()/parmsVo.getOutputDayQty())));
	    	usDailyVo.setDeath(numberFormat.format((int) death/parmsVo.getTotalRollingAvgDayQty()));
	    	usDailyVo.setDeathIncrease(numberFormat.format((int) deathIncrease/(parmsVo.getChangeRollingAvgDayQty()/parmsVo.getOutputDayQty())));
	    	usDailyVo.setHospitalizedCurrently(numberFormat.format(clientUsDailyVoList[i].getHospitalizedCurrently()));
	    	
		    if (totalTestResultsIncrease > 0) {
			    usDailyVo.setPositiveTestRate(percentFormat.format((float) 100 * positiveIncrease / totalTestResultsIncrease));
		    } else {
		    	usDailyVo.setPositiveTestRate("NaN");
		    }
	    	
	    	usDailyVoList.add(usDailyVo);
	    	
	    	if (usDailyVoList.size() >= parmsVo.getMaxRowsToReturn()) {
	    		break;
	    	}
	    }
		
		return usDailyVoList;
	}
	
	/**
	 * @return
	 */
	public List<UsCurrentVo> getUsCurrent() {
		List<UsCurrentVo> usDailyVoList = new ArrayList<UsCurrentVo>();
		
	    String targetUrl = "https://covidtracking.com/api/v1/states/current.json";

	    
	    Client client = ClientBuilder.newClient();
	    Response response = client.target(targetUrl).request().get();
	    ClientUsCurrentVo[] clientUsCurrentVoList = response.readEntity(ClientUsCurrentVo[].class);

	    response.close();
	    client.close();
	    
	    for (int i =0; i < clientUsCurrentVoList.length; i++) {
	    	UsCurrentVo usCurrentVo = new UsCurrentVo();
	    	float posRate = 0;
	    	
	    	StateVo stateVo = getStateVo(clientUsCurrentVoList[i].getState(), covidUtil.buildStateList());
	    	if (stateVo==null) {
	    		continue;
	    	}
	    	
	    	System.out.println("Working on state:" + stateVo.getStateNm());
	    	
	    	usCurrentVo.setState(stateVo.getStateNm());
	    	usCurrentVo.setPositive(numberFormat.format(clientUsCurrentVoList[i].getPositive()));
	    	double ppm = (double) clientUsCurrentVoList[i].getPositive() * 1000000 / stateVo.getStatePop();
	    	usCurrentVo.setPositivePerMillion(doubleFormat.format(ppm));
	    	usCurrentVo.setDeath(numberFormat.format(clientUsCurrentVoList[i].getDeath()));
	    	double dpm = (double) clientUsCurrentVoList[i].getDeath() * 1000000 / stateVo.getStatePop();
	    	usCurrentVo.setDeathPerMillion(doubleFormat.format(dpm));
	    	usCurrentVo.setHospitalizedCurrently(numberFormat.format(clientUsCurrentVoList[i].getHospitalizedCurrently()));
	    	double hpm = (double) clientUsCurrentVoList[i].getHospitalizedCurrently() * 1000000 / stateVo.getStatePop();
	    	usCurrentVo.setHospitalizedCurrentlyPerMillion(doubleFormat.format(hpm));
	    	if (clientUsCurrentVoList[i].getTotalTestResults() > 0) {
	    		System.out.println("Total Test Results:" + clientUsCurrentVoList[i].getTotalTestResults());
	    		System.out.println("Positive Test Results:" + clientUsCurrentVoList[i].getPositive());
		    	posRate = (float) 100 * clientUsCurrentVoList[i].getPositive() / clientUsCurrentVoList[i].getTotalTestResults();
	    		usCurrentVo.setPositiveTestRate(percentFormat.format(posRate));
	    	} else {
	    		usCurrentVo.setPositiveTestRate("0");
	    	}
	    	
	    	usDailyVoList.add(usCurrentVo);
	    }
		
		return usDailyVoList;
	}
	
	
	/**
	 * @param stateAbbr
	 * @param stateList
	 * @return
	 */
	private StateVo getStateVo(String stateAbbr, List<StateVo> stateList) {
		for (StateVo stateListVo : stateList) {
			if (stateAbbr.equals(stateListVo.getStateCd())) {
				return stateListVo;
			}
		}
		
		return null;
	}
	
	protected List<ClientUsDailyVo> getAllData() {
		List<ClientUsDailyVo> allDataList = new ArrayList<ClientUsDailyVo>();
		
	    String targetUrl = "https://covidtracking.com/api/v1/states/daily.json";
		
	    Client client = ClientBuilder.newClient();
	    Response response = client.target(targetUrl).request().get();
	    ClientUsDailyVo[] clientUsDailyVoList = response.readEntity(ClientUsDailyVo[].class);

	    response.close();
	    client.close();
	    
	    allDataList = Arrays.asList(clientUsDailyVoList);
	    
	    return allDataList;
	    
	    
	}
	
	/**
	 * @param allDataList
	 * @param stateVo
	 * @param queryDays
	 * @return
	 */
	public UsCurrentVo getSingleStateRecent(List<ClientUsDailyVo> allDataList, StateVo stateVo, int queryDays) {
		List<ClientUsDailyVo> filteredDataList = new ArrayList<ClientUsDailyVo>();
		int matchCount = 0;
		for (ClientUsDailyVo vo : allDataList) {
			if (vo.getState().equals(stateVo.getStateCd())) {
				filteredDataList.add(vo);
				matchCount++;
				if (matchCount >= queryDays) {
					break;
				}
			}
			
		}
		
		return calcRecentStateValues(filteredDataList, stateVo, queryDays);		
	}
	
	/**
	 * @param clientUsDailyVoList
	 * @param stateVo
	 * @param queryDays
	 * @return
	 */
	public UsCurrentVo calcRecentStateValues(List<ClientUsDailyVo> clientUsDailyVoList, StateVo stateVo, int queryDays) {
		UsCurrentVo usCurrentVo = new UsCurrentVo();
	    
	    int recentPositives = 0;
	    int recentDeaths = 0;
	    int recentTests = 0;
	    int hospitalizedCurrently = 0;
	    boolean firstRecord = true;
	    
	    for (ClientUsDailyVo vo : clientUsDailyVoList) {
	    	
	    	if (firstRecord) {
	    		hospitalizedCurrently += vo.getHospitalizedCurrently();
	    		firstRecord = false;
	    	}
	    	
	    	recentPositives += vo.getPositiveIncrease();
	    	recentDeaths += vo.getDeathIncrease();
	    	recentTests += vo.getTotalTestResultsIncrease();
	    }
	    
	    usCurrentVo.setState(stateVo.getStateNm());
	    usCurrentVo.setPositive(numberFormat.format(recentPositives));
	    usCurrentVo.setPositivePerMillion(doubleFormat.format ((double) recentPositives * ((double) 1000000 / stateVo.getStatePop())));
	    usCurrentVo.setDeath(numberFormat.format(recentDeaths));
	    usCurrentVo.setDeathPerMillion(doubleFormat.format((double) recentDeaths * ((double) 1000000 / stateVo.getStatePop())));
	    usCurrentVo.setHospitalizedCurrently(numberFormat.format(hospitalizedCurrently));
	    usCurrentVo.setHospitalizedCurrentlyPerMillion(doubleFormat.format((double) hospitalizedCurrently * ((double) 1000000 / stateVo.getStatePop())));

	    if (recentTests > 0) {
		    usCurrentVo.setPositiveTestRate(percentFormat.format((float) 100 * recentPositives / recentTests));
	    } else {
	    	usCurrentVo.setPositiveTestRate("NaN");
	    }
		
		return usCurrentVo;
	}
	
	/**
	 * @param queryDays
	 * @return
	 */
	public List<UsCurrentVo> getAllStateRecent(int queryDays) {
		List<UsCurrentVo> recentStateList = new ArrayList<UsCurrentVo>();
		List<StateVo> stateVoList = covidUtil.buildStateList();
		List<ClientUsDailyVo> allDatalist = getAllData();
		for (StateVo stateVo: stateVoList) {
			UsCurrentVo usCurrentVo = getSingleStateRecent(allDatalist, stateVo, queryDays);
			recentStateList.add(usCurrentVo);
			
		}
		
		return recentStateList;		
	}
	
	
	

}
