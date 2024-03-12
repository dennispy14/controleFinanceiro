package com.dennispy.controleFinanceiro.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "desc")
    private String descricao;
    @Column(name = "ano")
    private String ano;
    @Column(name = "mes")
    private String mes;
    @Column(name = "valor")
    private BigDecimal valor;

}
