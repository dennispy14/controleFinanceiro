package com.dennispy.controleFinanceiro.api.lancamentoResource.controller;


import com.dennispy.controleFinanceiro.api.lancamentoResource.dto.LancamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequestMapping("/api/lancamento")
public interface LancamentoApi {

    @GetMapping
    public List<LancamentoDto> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> getById(@PathVariable(value = "id") Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoDto insert(@Validated @RequestBody LancamentoDto lancamento);

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> update(@PathVariable(value = "id") Long id,
                                                       @Validated @RequestBody LancamentoDto lancamento);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id);
}
