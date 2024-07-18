package com.dennispy.controleFinanceiro.api.usuarioResource.mapper;

import com.dennispy.controleFinanceiro.api.usuarioResource.dto.UsuarioDto;
import com.dennispy.controleFinanceiro.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioDto toDto(Usuario usuario);
    Usuario toEntity(UsuarioDto usuarioDto);
}
