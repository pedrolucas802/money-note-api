package br.unifor.resource;

import br.unifor.model.dto.ClienteEBSDto;
import br.unifor.model.dto.RetornoDto;
import br.unifor.service.IntegradorEBSService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("integrador-ebs")
public class IntegradorEBSResource {

    @Inject
    IntegradorEBSService integradorEBSService;

    @Path("/cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RetornoDto integraCliente( ClienteEBSDto cliente){
        return this.integradorEBSService.integraCliente(cliente.matricula(), cliente.idPessoa());
    }

}
