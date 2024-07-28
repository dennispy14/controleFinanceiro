package com.dennispy.controleFinanceiro.api.lancamentoResource.controller;


import com.dennispy.controleFinanceiro.api.lancamentoResource.dto.LancamentoDto;
import com.dennispy.controleFinanceiro.api.lancamentoResource.mapper.LancamentoMapper;
import com.dennispy.controleFinanceiro.domain.lancamento.model.Lancamento;
import com.dennispy.controleFinanceiro.domain.lancamento.service.LancamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class LancamentoApiImp implements LancamentoApi {

    private final LancamentoService service;
    private final LancamentoMapper mapper;
    @Override
    public List<LancamentoDto> getAll() {
        return service.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LancamentoDto> getById(Long id) {
        Optional<Lancamento> lancamento = service.findById(id);
        if (lancamento.isPresent()) {
            return ResponseEntity.ok().body(mapper.toDto(lancamento.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public LancamentoDto insert(LancamentoDto lancamento) {
        return mapper.toDto(service.save(mapper.toEntity(lancamento)));
    }

    @Override
    public ResponseEntity<LancamentoDto> update(Long id, LancamentoDto lancamentoDto) {
        lancamentoDto.setId(id);
        if(service.findById(id).isPresent()) {
            return ResponseEntity.ok().body(mapper.toDto(service.save(mapper.toEntity(lancamentoDto))));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void delete(Long id) {
        service.deleteById(id);
    }
}
