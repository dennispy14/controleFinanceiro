package com.dennispy.controleFinanceiro.domain.lancamento.service;

import com.dennispy.controleFinanceiro.domain.lancamento.model.Lancamento;
import com.dennispy.controleFinanceiro.domain.lancamento.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    public Optional<Lancamento> findById(Long id) {
        return lancamentoRepository.findById(id);
    }

    public Lancamento save(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public void deleteById(Long id) {
        lancamentoRepository.deleteById(id);
    }
}