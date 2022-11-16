package br.pb.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RegisterForReflection
@Entity
@Table(name = "investment")
public class Investment {
    @Id
    private Long idInvestment;
    @Column
    private String description;
    @Column
    private BigDecimal value;
    @Column
    private Date investmentDate;

}

