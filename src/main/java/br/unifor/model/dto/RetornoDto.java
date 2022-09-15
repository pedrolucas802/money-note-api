package br.unifor.model.dto;


import javax.validation.constraints.NotNull;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public record RetornoDto(String situacao, String mensagem, String erro) {

    @NotNull
    public static RetornoDto getRetornoDto(Long idTitulo, Long idPessoa, String nrMatricula, CallableStatement call) throws SQLException {
        call.setLong(1, idTitulo);
        call.setLong(2, idPessoa);
        call.setString(3, nrMatricula);

        call.registerOutParameter(4, Types.VARCHAR);
        call.registerOutParameter(5, Types.VARCHAR);
        call.execute();

        var situacao = call.getString(4);
        var mensagem = call.getString(5);
        call.close();
        return new RetornoDto(situacao, mensagem, "N/A");
    }


}

