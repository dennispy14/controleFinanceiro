package com.dennispy.controleFinanceiro.domain.lancamento.repository;

import com.dennispy.controleFinanceiro.domain.lancamento.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {
}
