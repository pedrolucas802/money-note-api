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
public class IntegradorClienteEBSRepository {


    private final String SQL_CLIENTE_EBS = """ 
                                    call ca.pk_cad_cliente_ebs_api.p_integra_cliente_ebs(
                                            p_nr_matricula => ?, 
                                            p_id_pessoa =>  ?,
                                            p_fg_retorno => ?, 
                                            p_ds_retorno => ?) 
                                """;

    @Inject
    DataSource ds;


    public RetornoDto integraClienteEBS(Long matricula, Long idPessoa) {

        try (Connection conn = ds.getConnection()) {
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

                return new RetornoDto(situacao, mensagem);
            }

        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
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