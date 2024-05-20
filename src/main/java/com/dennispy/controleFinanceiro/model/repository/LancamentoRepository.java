package com.dennispy.controleFinanceiro.model.repository;

import com.dennispy.controleFinanceiro.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {
}
