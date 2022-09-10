package br.unifor.repository;

import br.unifor.model.dto.RetornoDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import static br.unifor.model.dto.RetornoDto.getRetornoDto;

@ApplicationScoped
public class IntegradorTransacaoEBSRepository {
    @Inject
    DataSource ds;

    private final String SQL_TRANSACAO_EBS = """ 
                call ca.pk_gvs_titulo_ebs_api.p_integra_titulo_ebs   (
                         p_id_titulo  => ?
                       , p_id_pessoa  => ?
                       , p_nr_matricula  => ?
                       , p_fg_retorno  => ?
                       , p_ds_retorno  => ?
                       )
                       
                       
            """;
    private final String SQL_AJUSTE_TRANSACAO_EBS = """ 
                call ca.pk_gvs_ajuste_ebs_api.p_integra_ajuste_ebs   (
                        p_id_titulo     => ?
                        , p_id_pessoa   => ?
                        , p_nr_matricula    => ?
                        , p_fg_retorno    => ?
                        , p_ds_retorno     => ?
                        )
                       
                       
            """;



    public RetornoDto integraTransacaoEBS(Long idTitulo, Long idPessoa, String nrMatricula) {


        try (Connection conn = ds.getConnection()) {
            try(CallableStatement call = conn.prepareCall(this.SQL_TRANSACAO_EBS)){

                return getRetornoDto(idTitulo, idPessoa, nrMatricula, call);
            }

        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }


    public RetornoDto ajusteTransacaoEBS (Long idTitulo, Long idPessoa, String nrMatricula) {
        try (Connection conn = ds.getConnection()) {
            try (CallableStatement call = conn.prepareCall(this.SQL_AJUSTE_TRANSACAO_EBS)) {

                return getRetornoDto(idTitulo, idPessoa, nrMatricula, call);
            }

        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}