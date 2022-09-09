package br.unifor.service;

import br.unifor.model.dto.RetornoDto;
import br.unifor.repository.IntegradorClienteEBSRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IntegradoraClienteEBSService {

    @Inject
    IntegradorClienteEBSRepository integradorEBSRepository;

    public RetornoDto integraCliente(Long matricula, Long idPessoa){
        return this.integradorEBSRepository.integraClienteEBS(matricula, idPessoa);
    }


}
