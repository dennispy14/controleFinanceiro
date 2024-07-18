package com.dennispy.controleFinanceiro.api.usuarioResource.controller;

import com.dennispy.controleFinanceiro.Service.UsuarioService;
import com.dennispy.controleFinanceiro.api.usuarioResource.dto.UsuarioDto;
import com.dennispy.controleFinanceiro.api.usuarioResource.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class UsuarioApiImp implements UsuarioApi{

    private final UsuarioService service;
    private final UsuarioMapper mapper;
    @Override
    public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioDto dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvarUsuario(mapper.toEntity(dto))));
    }
}
