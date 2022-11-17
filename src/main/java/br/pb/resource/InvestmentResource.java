package br.pb.resource;

import br.pb.dto.ReturnMsg;
import br.pb.model.Investment;
import br.pb.service.InvestmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/investment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestmentResource {

    @Inject
    InvestmentService service;

    @POST
    @Path("/")
    public Response save(Investment investment) {
        service.save(investment);
        return Response.status(201).entity(new ReturnMsg("Investment added.", true)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok().entity(new ReturnMsg("Investment listed", true, service.findAll())).build();
    }

    @GET
    @Path("/paged")
    public Response findAllPaged() {
        return Response.ok().entity(new ReturnMsg("Investment listed", true, service.findAllPaged())).build();
    }

    @GET
    @Path("/{idInvestment}")
    public Response findOne(@PathParam("idInvestment") Long idInvestment) {
        return Response.ok().entity(new ReturnMsg("Investment listed", true, service.findOne(idInvestment))).build();
    }


    @PUT
    @Path("/{idInvestment}")
    public Response update(@PathParam("idInvestment") long idInvestment, Investment investment) {
        service.update(idInvestment, investment);
        return Response.ok().entity(new ReturnMsg("Investment edited.", true)).build();
    }

    @DELETE
    @Path("/{idInvestment}")
    public Response delete(@PathParam("idInvestment") Long idInvestment) {
        service.delete(idInvestment);
        return Response.ok().entity(new ReturnMsg("Investment deleted.", true)).build();
    }
}