package com.event.eagle.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.event.eagle.dto.SeatingOrder;
import com.event.eagle.service.GeneticAlgorithmForSeating;

@Path("/generateseating")
public class RestServiceGenerateSeating {

	@GET
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_JSON})

	public Response getMsg(@PathParam("param") String id) {

		
		GeneticAlgorithmForSeating geneticAlgorithmForSeating = new GeneticAlgorithmForSeating();
		GenericEntity<List<SeatingOrder>> entity = new GenericEntity<List<SeatingOrder>>(geneticAlgorithmForSeating.evaluation(Integer.parseInt(id))) {};

		return Response.status(200).entity(entity).build();

	}
}
