package br.unifor.model.entity;
import br.unifor.model.dto.RetornoErroClienteDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ca.cad_integra_cliente_log_ebs")
@SqlResultSetMapping(
        name="retornoErroClienteMapping",
        classes={
                @ConstructorResult(
                        targetClass= RetornoErroClienteDto.class,
                        columns={
                                @ColumnResult(name="erro_json", type = String.class),
                        }
                )
        }
)

public class RetornoErroCliente {
    @Id
    Long idClienteLogEbs;
    Long nrTransacao;
    String tipo;
    String dadoJson;
    Date creationDate;
    String integrado;

}
