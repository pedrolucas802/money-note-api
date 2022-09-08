package br.unifor.model.dto;

import java.util.Collection;

public record ModalidadeAlunoExcecao(Collection<ModalidadeExcecao> modalidades,
                                     Collection<ModalidadeTipoExcecao> tipos) {
}

