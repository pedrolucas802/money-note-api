package br.pb.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RegisterForReflection
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    private Long idPurchase;
    @Column
    private String description;
    @Column
    private BigDecimal value;
    @Column
    private Date purchaseDate;
    @Column
    private boolean purchaseConst;
}
