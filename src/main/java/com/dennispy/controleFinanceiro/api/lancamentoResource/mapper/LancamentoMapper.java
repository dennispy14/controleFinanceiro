package com.dennispy.controleFinanceiro.api.lancamentoResource.mapper;

import com.dennispy.controleFinanceiro.api.lancamentoResource.dto.LancamentoDto;
import com.dennispy.controleFinanceiro.domain.lancamento.model.Lancamento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LancamentoMapper {
    LancamentoDto toDto(Lancamento lancamento);
    Lancamento toEntity(LancamentoDto lancamentoDto);

}
