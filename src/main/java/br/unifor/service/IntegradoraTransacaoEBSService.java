package br.unifor.service;

import br.unifor.model.dto.RetornoDto;
import br.unifor.repository.IntegradorClienteEBSRepository;
import br.unifor.repository.IntegradorTransacaoEBSRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IntegradoraTransacaoEBSService {

    @Inject
    IntegradorTransacaoEBSRepository integradorTransacaoEBSRepository;


    public RetornoDto integraTransacao(Long idTitulo, Long idPessoa, String nrMatricula){
        return this.integradorTransacaoEBSRepository.integraTransacaoEBS(idTitulo, idPessoa, nrMatricula );
    }

    public RetornoDto ajusteTransacaoEBS(Long idTitulo, Long idPessoa, String nrMatricula){
        return this.integradorTransacaoEBSRepository.ajusteTransacaoEBS(idTitulo, idPessoa, nrMatricula );
    }
}
