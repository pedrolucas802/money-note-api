package br.unifor.resource;

import br.unifor.model.dto.ClienteEBSDto;
import br.unifor.model.dto.RecebimentoEBSDto;
import br.unifor.model.dto.RetornoDto;
import br.unifor.model.dto.TransacaoEBSDto;
import br.unifor.service.IntegradoraClienteEBSService;
import br.unifor.service.IntegradoraRecebimentoEBSService;
import br.unifor.service.IntegradoraTransacaoEBSService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("integrador-ebs")
public class TransacaoEBSResource {

    @Inject
    IntegradoraTransacaoEBSService transacaoService;

    @Path("/transacao")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraTransacao(TransacaoEBSDto transacao){
        return this.transacaoService.integraTransacao(transacao.idTitulo(), transacao.idPessoa(), transacao.nrMatricula());
    }

    @Path("/transacao/ajuste")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto ajusteTransacao(TransacaoEBSDto ajuste){
        return this.transacaoService.ajusteTransacaoEBS(ajuste.idTitulo(), ajuste.idPessoa(), ajuste.nrMatricula());
    }

}
