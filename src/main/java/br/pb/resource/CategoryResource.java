package br.pb.resource;

import br.pb.dto.ReturnMsg;
import br.pb.model.Category;
import br.pb.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService service;

    @POST
    @Path("/")
    public Response save(Category category) {
        service.save(category);
        return Response.status(201).entity(new ReturnMsg("Category added.", true)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok().entity(new ReturnMsg("Category listed", true, service.findAll())).build();
    }

    @GET
    @Path("/paged")
    public Response findAllPaged() {
        return Response.ok().entity(new ReturnMsg("Category listed", true, service.findAllPaged())).build();
    }

    @GET
    @Path("/{idCategory}")
    public Response findOne(@PathParam("idCategory") Long idCategory) {
        return Response.ok().entity(new ReturnMsg("Category listed", true, service.findOne(idCategory))).build();
    }


    @PUT
    @Path("/{idCategory}")
    public Response update(@PathParam("idCategory") long idCategory, Category category) {
        service.update(idCategory, category);
        return Response.ok().entity(new ReturnMsg("Category edited.", true)).build();
    }

    @DELETE
    @Path("/{idCategory}")
    public Response delete(@PathParam("idCategory") Long idCategory) {
        service.delete(idCategory);
        return Response.ok().entity(new ReturnMsg("Category deleted.", true)).build();
    }
}