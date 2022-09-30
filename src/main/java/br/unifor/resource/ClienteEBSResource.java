package br.unifor.resource;

import br.unifor.model.dto.*;
import br.unifor.service.IntegradoraClienteEBSService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.Long;

@Path("integrador-ebs")
public class ClienteEBSResource {
    @Inject
    IntegradoraClienteEBSService clienteService;


    @Path("/cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraCliente(ClienteEBSDto cliente){
        return this.clienteService.integraCliente(cliente.matricula(), cliente.idPessoa());
    }

    @Path("/cliente-erro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoErroClienteDto RetornoErroClienteDto(ClienteEBSDto cliente){
        return this.clienteService.RetornoErroCliente(cliente.matricula(), cliente.idPessoa());
    }

    @Path("/cliente-titulo/{idTitulo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraClienteTitulo(@PathParam("idTitulo") Long idTitulo){
        return this.clienteService.integraClienteTitulo(idTitulo);
    }




}
