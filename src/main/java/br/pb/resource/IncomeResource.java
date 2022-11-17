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

    @GET
    @Path("/paged")
    public Response findAllPaged() {
        return Response.ok().entity(new ReturnMsg("Income listed", true, service.findAllPaged())).build();
    }

    @GET
    @Path("/{idIncome}")
    public Response findOne(@PathParam("idIncome") Long idIncome) {
        return Response.ok().entity(new ReturnMsg("Income listed", true, service.findOne(idIncome))).build();
    }


    @PUT
    @Path("/{idIncome}")
    public Response update(@PathParam("idIncome") long idIncome, Income income) {
        service.update(idIncome, income);
        return Response.ok().entity(new ReturnMsg("Income edited.", true)).build();
    }

    @DELETE
    @Path("/{idIncome}")
    public Response delete(@PathParam("idIncome") Long idIncome) {
        service.delete(idIncome);
        return Response.ok().entity(new ReturnMsg("Income deleted.", true)).build();
    }
}