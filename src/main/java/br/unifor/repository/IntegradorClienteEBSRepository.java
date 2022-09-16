package br.unifor.repository;

import br.unifor.model.dto.RetornoDto;
import br.unifor.model.dto.RetornoErroClienteDto;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

@ApplicationScoped
public class IntegradorClienteEBSRepository {


    private final String SQL_CLIENTE_EBS = """ 
                                    call ca.pk_cad_cliente_ebs_api.p_integra_cliente_ebs(
                                            p_nr_matricula => ?, 
                                            p_id_pessoa =>  ?,
                                            p_fg_retorno => ?, 
                                            p_ds_retorno => ?) 
    
    
                                """;
    @ConfigProperty(name="oracle.session")
    private String sessionOracle;
    @Inject
    DataSource ds;

    @Inject
    EntityManager entity;


    public RetornoDto integraClienteEBS(Long matricula, Long idPessoa) {

        try (Connection conn = ds.getConnection()) {
            try(Statement statement = conn.createStatement()) {
                for (String strsql : sessionOracle.split(";")) {
                    statement.addBatch(strsql);
                }
                statement.executeBatch();
            }

            try(CallableStatement call = conn.prepareCall(this.SQL_CLIENTE_EBS)){

                call.setLong(1, matricula);
                call.setLong(2, idPessoa);
                call.registerOutParameter(3, Types.VARCHAR);
                call.registerOutParameter(4, Types.CLOB);
                call.execute();

                var situacao = call.getString(3);
                Clob cl = call.getClob(4);
                var mensagem = clobToString(cl);
                call.close();

                return new RetornoDto(situacao, mensagem,"N/A");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public RetornoErroClienteDto ErroIntegraCliente( Long nrMatricula, Long idPessoa ){

        String SQL_ERRO_INTEGRS_CLIENTE_CRM =  """
                SELECT DISTINCT to_char(cicle.dados_json) as "erro_json" FROM (
                SELECT jt.nr_matricula, jt.id_pessoa, cad.nr_transacao
                FROM cad_integra_cliente_log_ebs cad,
                
                JSON_TABLE(dados_json, '$[*]'
                COLUMNS (
                         nr_matricula VARCHAR2(10) PATH '$.p_nr_matricula',
                         id_pessoa VARCHAR2(20) PATH '$.p_id_pessoa'))    
                AS jt) cadjson INNER JOIN
                    cad_integra_cliente_log_ebs cicle on cicle.nr_transacao = cadjson.nr_transacao
                 where
                     cicle.tipo = 'OUT'
                and cicle.nr_transacao in 
            (select 
                nr_transacao 
            from 
                cad_integra_cliente_log_ebs 
            where 
                integrado = 'S' 
                and id_pessoa = 366411 
                and nr_matricula =2223004 )
                 """;


        return (RetornoErroClienteDto) entity.createNativeQuery(SQL_ERRO_INTEGRS_CLIENTE_CRM, "retornoErroClienteMapping").getSingleResult();

    }

    private String clobToString(Clob cl) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(cl.getAsciiStream(),  StandardCharsets.ISO_8859_1))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}