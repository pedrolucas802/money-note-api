package br.unifor.model.excecao;

import java.util.Collection;

public record ModalidadeAlunoExcecao(Collection<ModalidadeExcecao> modalidades,
                                     Collection<ModalidadeTipoExcecao> tipos) {
}

