package com.dennispy.controleFinanceiro.components.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLancamento {

    PENDENTE("PENDENTE"),
    CANCELADO("CANCELADO"),
    EFETIVADO("EFETIVADO");

    private final String tipo;
}
