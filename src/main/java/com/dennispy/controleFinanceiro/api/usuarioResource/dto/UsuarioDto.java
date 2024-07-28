package com.dennispy.controleFinanceiro.api.usuarioResource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {

    private Integer id;
    private String email;
    private String nome;
    private String senha;
}
