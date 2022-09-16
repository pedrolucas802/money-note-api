package br.unifor.service;

import br.unifor.model.dto.RetornoDto;
import br.unifor.model.dto.RetornoErroClienteDto;
import br.unifor.repository.IntegradorClienteEBSRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class IntegradoraClienteEBSService {

    @Inject
    IntegradorClienteEBSRepository integradorEBSRepository;

    public RetornoDto integraCliente(Long matricula, Long idPessoa){
        RetornoDto call = this.integradorEBSRepository.integraClienteEBS(matricula, idPessoa);
        if(Objects.equals(call.situacao(), "N")){
            return new RetornoDto(call.situacao(), call.mensagem(), String.valueOf(this.integradorEBSRepository.ErroIntegraCliente(matricula, idPessoa)));
        }else{
            return call;
        }
    }

    public RetornoErroClienteDto RetornoErroCliente(Long matricula, Long idPessoa){
        return this.integradorEBSRepository.ErroIntegraCliente(matricula, idPessoa);
    }

}
