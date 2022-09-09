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

@ApplicationScoped
public class IntegradorTransacaoEBSRepository {


    private final String SQL_TRANSACAO_EBS = """ 
                call ca.pk_gvs_titulo_ebs_api.p_integra_titulo_ebs   (
                         p_id_titulo  => ?
                       , p_id_pessoa  => ?
                       , p_nr_matricula  => ?
                       , p_fg_retorno  => ?
                       , p_ds_retorno  => ?
                       )
                       
                       
            """;

    @Inject
    DataSource ds;

    public RetornoDto integraTransacaoEBS(Long idTitulo, Long idPessoa, String nrMatricula) {


        try (Connection conn = ds.getConnection()) {
            try(CallableStatement call = conn.prepareCall(this.SQL_TRANSACAO_EBS)){

                call.setLong(1, idTitulo);
                call.setLong(2, idPessoa);
                call.setString(3, nrMatricula);

                call.registerOutParameter(4, Types.VARCHAR);
                call.registerOutParameter(5, Types.VARCHAR);
                call.execute();

                var situacao = call.getString(4);
                var mensagem = call.getString(5);
                call.close();
                return new RetornoDto(situacao, mensagem);
            }

        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}