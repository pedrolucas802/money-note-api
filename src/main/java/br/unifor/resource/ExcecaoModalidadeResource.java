package br.unifor.resource;

import br.unifor.model.excecao.ModalidadeAlunoExcecao;
import br.unifor.model.excecao.ModalidadeExcecao;
import br.unifor.service.ModalidadeExcecaoService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("/excecao")
public class ExcecaoModalidadeResource {

    private ModalidadeExcecaoService modalidadeExcecaoService;

    public ExcecaoModalidadeResource(ModalidadeExcecaoService modalidadeExcecaoService){
        this.modalidadeExcecaoService = modalidadeExcecaoService;
    }

    @GET
    @Path("/tipo/{tipoModalidade}")
    public ModalidadeAlunoExcecao listaExcecoesPorTipo(Long tipoModalidade){
        return this.modalidadeExcecaoService.regraExcecaoTipoModalidade(tipoModalidade);
    }

    @GET
    @Path("/{idModalidade}")
    public ModalidadeAlunoExcecao listaExcecoes(Long idModalidade){
        return this.modalidadeExcecaoService.regraExcecaoModalidade(idModalidade);
    }

    @GET
    @Path("/regra/{idModalidade}")
    public Collection<ModalidadeExcecao> regraExcecoes(Long idModalidade){
        return this.modalidadeExcecaoService.regraModalidade(idModalidade);
    }
}
