package br.unifor.service;

import br.unifor.model.dto.ModalidadeAlunoExcecao;
import br.unifor.model.dto.ModalidadeExcecao;
import br.unifor.repository.mapper.ModalidadeExcecaoMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class ModalidadeExcecaoService {

    private ModalidadeExcecaoMapper modalidadeExcecaoMapper;

    public ModalidadeExcecaoService(ModalidadeExcecaoMapper modalidadeExcecaoMapper){
        this.modalidadeExcecaoMapper = modalidadeExcecaoMapper;
    }

    public ModalidadeAlunoExcecao regraExcecaoTipoModalidade(Long idTipoModalidade){
        var tipos = this.modalidadeExcecaoMapper.getExcecoesTipoModalidadeTipos(idTipoModalidade);
        var modalidades = this.modalidadeExcecaoMapper.getExcecoesTipoModalidade(idTipoModalidade);
        return new ModalidadeAlunoExcecao(modalidades, tipos);
    }

    public ModalidadeAlunoExcecao regraExcecaoModalidade(Long idModalidade){
        var tipos = this.modalidadeExcecaoMapper.getExcecoesModalidadeTipos(idModalidade);
        var modalidades = this.modalidadeExcecaoMapper.getExcecoesModalidade(idModalidade);
        return new ModalidadeAlunoExcecao(modalidades, tipos);
    }

    public Collection<ModalidadeExcecao> regraModalidade(Long idModalidade) {
        return this.modalidadeExcecaoMapper.regraModaldiade(idModalidade);
    }
}
