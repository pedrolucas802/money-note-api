package br.pb.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RegisterForReflection
@Entity
@Table(name = "income")
public class Income {
    @Id
    private Long idIncome;
    @Column
    private String description;
    @Column
    private BigDecimal value;
    @Column
    private Timestamp icomeDate;
    @Column
    private boolean incomeConst;
}
