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

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.illinus.util.CovidUtil;
import com.illinus.vo.CdcUsDailyVo;
import com.illinus.vo.ParmsVo;
import com.illinus.vo.UsDailyVo;

public class CdcService {
	
    private static NumberFormat numberFormat = NumberFormat.getInstance();
    private static NumberFormat doubleFormat = NumberFormat.getInstance();
    private static NumberFormat percentFormat = NumberFormat.getInstance();
    
    static final Logger logger = Logger.getLogger(CdcService.class);
    
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
	public List<UsDailyVo> getStateDaily(ParmsVo parmsVo) {
		List<UsDailyVo> usDailyVoList = new ArrayList<UsDailyVo>();
		
	    String targetUrl;
	    if (parmsVo.getStateCd()==null) {
	    	targetUrl = "https://data.cdc.gov/resource/9mfq-cb36.json?state=MN";
	    } else {
	    	targetUrl = "https://data.cdc.gov/resource/9mfq-cb36.json?state=" + parmsVo.getStateCd().toUpperCase();
	    }
	    SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat sdfOut = new SimpleDateFormat("MMM dd");
	    
	    Client client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
	    Response response = client.target(targetUrl).request().get();
	    CdcUsDailyVo[] cdcVoArray = response.readEntity(CdcUsDailyVo[].class);

	    response.close();
	    client.close();
	    
	    //TODO: sort the array
	    Arrays.sort(cdcVoArray);
	    
    	logger.debug("array size:" + cdcVoArray.length);
	    
	    for (int i =0; i < cdcVoArray.length; i++) {
	    	logger.debug("working on:" + i + "|" + cdcVoArray[i].toString());

	    	if (i % parmsVo.getOutputDayQty() != 0) {
	    		continue;
	    	}
	    	
	    	UsDailyVo usDailyVo = new UsDailyVo();
	    	try {
		    	Date d = sdfIn.parse(cdcVoArray[i].getSubmission_date().substring(0, 10));
		    	usDailyVo.setDate(sdfOut.format(d));	    		
	    	} catch (ParseException e) {
	    		usDailyVo.setDate(cdcVoArray[i].getSubmission_date());
	    	}
	    	int positive = 0;
	    	int positiveIncrease = 0;
	    	int death = 0;
	    	int deathIncrease = 0;
	    	int totalTestResultsIncrease = 0;

	    	for (int j = i; j < i + parmsVo.getTotalRollingAvgDayQty(); j++) {
	    		if (j < cdcVoArray.length ) {
		    		positive += cdcVoArray[j].getTot_cases();
		    		death += cdcVoArray[j].getTot_death();	    			
	    		}
	    	}
	    	
	    	for (int j = i; j < i + parmsVo.getChangeRollingAvgDayQty(); j++) {
	    		if (j < cdcVoArray.length ) {
		    		positiveIncrease += cdcVoArray[j].getNew_case();
		    		deathIncrease += cdcVoArray[j].getNew_death();
		    		totalTestResultsIncrease += cdcVoArray[j].getPnew_case();	    			
	    		}
	    	}
	    	
	    	usDailyVo.setPositive(numberFormat.format((int) positive/parmsVo.getTotalRollingAvgDayQty()));
	    	usDailyVo.setPositiveIncrease(numberFormat.format((int) positiveIncrease/(parmsVo.getChangeRollingAvgDayQty()/parmsVo.getOutputDayQty())));
	    	usDailyVo.setDeath(numberFormat.format((int) death/parmsVo.getTotalRollingAvgDayQty()));
	    	usDailyVo.setDeathIncrease(numberFormat.format((int) deathIncrease/(parmsVo.getChangeRollingAvgDayQty()/parmsVo.getOutputDayQty())));
	    	usDailyVo.setHospitalizedCurrently(numberFormat.format(0));
	    	
		    if (totalTestResultsIncrease > 0) {
			    usDailyVo.setPositiveTestRate(percentFormat.format((float) positiveIncrease / totalTestResultsIncrease));
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

}
