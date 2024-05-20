package com.dennispy.controleFinanceiro.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum TipoLancamento {
    DESPESA("DESPESA"),
    RECEITA("RECEITA");

    private final String tipo;

}
