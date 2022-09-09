package br.unifor.resource;

import br.unifor.model.dto.ClienteEBSDto;
import br.unifor.model.dto.RetornoDto;
import br.unifor.model.dto.TransacaoEBSDto;
import br.unifor.service.IntegradoraClienteEBSService;
import br.unifor.service.IntegradoraTransacaoEBSService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("integrador-ebs")
public class IntegradorEBSResource {

    @Inject
    IntegradoraClienteEBSService clienteService;

    @Inject
    IntegradoraTransacaoEBSService transacaoService;

    @Path("/cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraCliente(ClienteEBSDto cliente){
        return this.clienteService.integraCliente(cliente.matricula(), cliente.idPessoa());
    }

    @Path("/transacao")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraTransacao(TransacaoEBSDto transacao){
        return this.transacaoService.integraTransacao(transacao.idTitulo(), transacao.idPessoa(), transacao.nrMatricula());
    }


}
