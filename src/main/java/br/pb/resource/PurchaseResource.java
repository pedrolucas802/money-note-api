package br.pb.resource;

import br.pb.dto.ReturnMsg;
import br.pb.model.Purchase;
import br.pb.service.PurchaseService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    @Inject
    PurchaseService service;

    @POST
    @Path("/")
    public Response save(Purchase purchase) {
        service.save(purchase);
        return Response.status(201).entity(new ReturnMsg("Purchase added.", true)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok().entity(new ReturnMsg("Purchase listed", true, service.findAll())).build();
    }

    @GET
    @Path("/paged")
    public Response findAllPaged() {
        return Response.ok().entity(new ReturnMsg("Purchase listed", true, service.findAllPaged())).build();
    }

    @GET
    @Path("/{idPurchase}")
    public Response findOne(@PathParam("idPurchase") Long idPurchase) {
        return Response.ok().entity(new ReturnMsg("Purchase listed", true, service.findOne(idPurchase))).build();
    }


    @PUT
    @Path("/{idPurchase}")
    public Response update(@PathParam("idPurchase") long idPurchase, Purchase purchase) {
        service.update(idPurchase, purchase);
        return Response.ok().entity(new ReturnMsg("Purchase edited.", true)).build();
    }

    @DELETE
    @Path("/{idPurchase}")
    public Response delete(@PathParam("idPurchase") Long idPurchase) {
        service.delete(idPurchase);
        return Response.ok().entity(new ReturnMsg("Purchase deleted.", true)).build();
    }
}