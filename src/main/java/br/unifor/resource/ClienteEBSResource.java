package br.unifor.resource;

import br.unifor.model.dto.*;
import br.unifor.service.IntegradoraClienteEBSService;
import br.unifor.service.IntegradoraRecebimentoEBSService;
import br.unifor.service.IntegradoraTransacaoEBSService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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




}
