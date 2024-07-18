package com.dennispy.controleFinanceiro.api.usuarioResource.controller;

import com.dennispy.controleFinanceiro.api.usuarioResource.dto.UsuarioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/usuarios")
public interface UsuarioApi {

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    // @PreAuthorize("@securityService.hasPermission('pessoaApi_findByDocumento')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioDto dto);
}