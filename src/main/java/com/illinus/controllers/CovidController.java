package com.illinus.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.illinus.service.CovidService;
import com.illinus.vo.ParmsVo;
import com.illinus.vo.UsDailyVo;

@Path("/")
public class CovidController {
	
	private CovidService covidService = new CovidService();


    static final Logger logger = Logger.getLogger(CovidController.class);

    static class Entity {
        public int id = 1;
        public String name;

        public Entity(String name) {
            this.name = name;
        }
    }

    @GET
    public Response indexEndpoint(
    ) {
        logger.debug("in indexEndpoint");
        return Response.status(200)
                .entity(new Entity("John doe"))
                .build();
    }

    @GET
    @Path("/{name}")
    public Response exampleEndpoint(
            @PathParam("name") String name
    ) {

        logger.debug("in exampleEndpoint");
        return Response.status(201)
                .entity(new Entity(name))
                .build();
    }

    @GET
    @Path("/resource/{name}")
    public Response exampleSecondEndpoint(
            @PathParam("name") String name
    ) {

        logger.debug("in exampleSecondEndpoint");
        return Response.status(201)
                .entity(new Entity(name))
                .build();
    }
    
    @GET
    @Path("/states/statelist")
    public Response getStates(
            @PathParam("name") String name
    ) {

        logger.debug("in getStates");
        return Response.status(201)
                .entity(covidService.getStates())
                .build();
    }
    
    @GET
    @Path("/stateweekly/{stateCd}")
    //public List<UsDailyVo> getStateWeekly(
    public Response getStateWeekly(
    		@PathParam("name") String stateCd) {
    	
        logger.debug("in getStateWeekly");

    	ParmsVo parmsVo = new ParmsVo();
    	parmsVo.setStateCd(stateCd);
    	parmsVo.setMaxRowsToReturn(52);
    	parmsVo.setTotalRollingAvgDayQty(1);
    	parmsVo.setChangeRollingAvgDayQty(7);
    	parmsVo.setOutputDayQty(7);

        return Response.status(201)
                .entity(covidService.getUsDaily(parmsVo))
                .build();
  
    	/*
        return Response.status(201)
                .entity(new Entity(stateCd))
                .build();
                */
    }

    public static class NewEntityRequest {
        public String name;
    }

    /**
     * This controller uses automatically serialization of Request body to any POJO
     * @param requestEntity Request Entity
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/resource")
    public Response exampleSecondEndpointPost(
            NewEntityRequest requestEntity
    ) {

        logger.debug("in exampleSecondEndpointPost");
        return Response.status(201)
                .entity(new Entity(requestEntity.name))
                .build();
    }

}
