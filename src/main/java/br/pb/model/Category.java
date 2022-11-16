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
@Table(name = "category")
public class Category {
    @Id
    private Long idCategory;
    @Column
    private String description;


}

