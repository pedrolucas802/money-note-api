package br.unifor.service;

import br.unifor.model.dto.RetornoDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IntegradoraRecebimentoEBSService {

    @Inject IntegradoraRecebimentoEBSService integradoraRecebimentoEBSService;

    public RetornoDto integraRecebimento(Long idTitulo, Long idPessoa, String nrMatricula){
        return this.integradoraRecebimentoEBSService.integraRecebimento(idTitulo, idPessoa, nrMatricula );
    }

}
