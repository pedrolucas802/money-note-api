package br.unifor.repository;

import br.unifor.model.dto.RetornoDto;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import static br.unifor.model.dto.RetornoDto.getRetornoDto;

@ApplicationScoped
public class IntegradorRecebimentoEBSRepository {

    @Inject
    DataSource ds;

    private final String SQL_RECEBIMENTO_EBS = """ 
                call ca.pk_gvs_recebimento_ebs_api.p_integra_recebimento_ebs   (
                          p_id_titulo  => ?
                        , p_id_pessoa  => ?
                        , p_nr_matricula  => ?
                        , p_fg_retorno  => ?
                        , p_ds_retorno  => ?
                        )
                       
                       
            """;


    public RetornoDto integraRecebimentoEBS(Long idTitulo, Long idPessoa, String nrMatricula) {

        try (Connection conn = ds.getConnection()) {
            try(CallableStatement call = conn.prepareCall(this.SQL_RECEBIMENTO_EBS)){

                return getRetornoDto(idTitulo, idPessoa, nrMatricula, call);
            }

        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }



}
