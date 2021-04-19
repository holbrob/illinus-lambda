package com.illinus.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.illinus.service.CdcService;
import com.illinus.service.CovidService;
import com.illinus.vo.ParmsVo;
import com.illinus.vo.UsCurrentVo;
import com.illinus.vo.UsDailyVo;

@Path("/")
public class CovidController {
	
	private CovidService covidService = new CovidService();
	private CdcService cdcService = new CdcService();


    static final Logger logger = Logger.getLogger(CovidController.class);
    
    @GET
    @Path("/states/statelist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStates(
            @PathParam("name") String name) {
        return covidService.getStates();
    }
    
    @GET
    @Path("/states")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStates() {
        return covidService.getStates();
    }
    
    @GET
    @Path("/usdaily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsDailyVo> getUsDaily() {
    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setMaxRowsToReturn(90);
    	parmsVo.setTotalRollingAvgDayQty(7);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(1);
        return covidService.getUsDaily(parmsVo);
    }

    @GET
    @Path("/usweekly")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsDailyVo> getUsWeekly() {
    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setMaxRowsToReturn(52);
    	parmsVo.setTotalRollingAvgDayQty(1);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(7);
        return covidService.getUsDaily(parmsVo);
    }

    @GET
    @Path("/uscurrent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsCurrentVo> getUsCurrent() {
        return covidService.getUsCurrent();
    }
    
    @GET
    @Path("/stateweekly/{stateCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsDailyVo> getStateWeekly(
    		@PathParam("stateCd") String stateCd) {
    	
        logger.debug("in getStateWeekly");

    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setStateCd(stateCd);
    	parmsVo.setMaxRowsToReturn(52);
    	parmsVo.setTotalRollingAvgDayQty(1);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(7);
    	return covidService.getUsDaily(parmsVo);
    }
    
    @GET
    @Path("/statedaily/{stateCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsDailyVo> getStateDaily(@PathParam("stateCd") String stateCd) {
    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setStateCd(stateCd);
    	parmsVo.setMaxRowsToReturn(90);
    	parmsVo.setTotalRollingAvgDayQty(7);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(1);
    	return covidService.getUsDaily(parmsVo);
    }
    
    @GET
    @Path("/usrecent/{queryDays}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsCurrentVo> getUsRecent(@PathParam("queryDays") String queryDays) {
    	return covidService.getAllStateRecent(Integer.parseInt(queryDays));
    }
    
    @GET
    @Path("/v2/statedaily/{stateCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsDailyVo> getV2StateDaily(@PathParam("stateCd") String stateCd) {
    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setStateCd(stateCd);
    	parmsVo.setMaxRowsToReturn(90);
    	parmsVo.setTotalRollingAvgDayQty(7);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(1);
    	return cdcService.getStateDaily(parmsVo);
    }

}
