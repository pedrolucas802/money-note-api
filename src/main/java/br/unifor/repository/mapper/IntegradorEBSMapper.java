package br.unifor.repository.mapper;

import br.unifor.model.dto.RetornoDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface IntegradorEBSMapper {


    @Select("""
            {call ca.pk_cad_cliente_ebs_api.p_integra_cliente_ebs(
                 #{matricula, jdbcType=INTEGER, mode =  IN},
                 #{idPessoa , jdbcType=INTEGER, mode =  IN},
                 #{stRetorno, jdbcType=VARCHAR, mode = OUT},
                 #{dsRetorno, jdbcType=VARCHAR, mode = OUT}   
            )}
            """)
    @Options(statementType = StatementType.CALLABLE)
    @Results(
             value = {
            @Result(property = "matricula", column = "stRetorno"),
            @Result(property = "idPessoa", column = "dsRetorno")
        }
    )
    @ResultType(RetornoDto.class)
    RetornoDto integraCliente(@Param("matricula") Long matricula, @Param("idPessoa") Long idPessoa);

}
