package br.unifor.service;

import br.unifor.model.dto.RetornoDto;
import br.unifor.repository.IntegradorEBSRepository;
import br.unifor.repository.mapper.IntegradorEBSMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IntegradorEBSService {

    @Inject
    IntegradorEBSRepository integradorEBSRepository;

    public RetornoDto integraCliente(Long matricula, Long idPessoa){
        return this.integradorEBSRepository.integraClienteEBS(matricula, idPessoa);
    }

}
