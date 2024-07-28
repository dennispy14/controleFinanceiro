package com.dennispy.controleFinanceiro.api.lancamentoResource.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoDto {
    private Long id;

    private String descricao;

    private String ano;

    private String mes;

    private BigDecimal valor;
}
