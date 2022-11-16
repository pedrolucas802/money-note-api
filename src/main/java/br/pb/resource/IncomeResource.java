package br.pb.resource;

import br.pb.dto.ReturnMsg;
import br.pb.model.Income;
import br.pb.service.IncomeService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/income")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IncomeResource {

    @Inject
    IncomeService service;

    @POST
    @Path("/")
    public Response save(Income income) {
        service.save(income);
        return Response.status(201).entity(new ReturnMsg("Income added.", true)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok().entity(new ReturnMsg("Income listed", true, service.findAll())).build();
    }
}