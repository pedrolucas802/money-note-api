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
public class RecebimentoEBSResource {
    @Inject
    IntegradoraRecebimentoEBSService recebimentoService;

    @Path("/recebimento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraRecebimento(RecebimentoEBSDto recebimento){
        return this.recebimentoService.integraRecebimento(recebimento.idTitulo(), recebimento.idPessoa(), recebimento.nrMatricula());
    }

}
