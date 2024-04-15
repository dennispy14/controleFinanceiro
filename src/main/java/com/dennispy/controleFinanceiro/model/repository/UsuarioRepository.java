package com.dennispy.controleFinanceiro.model.repository;

import com.dennispy.controleFinanceiro.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);
}
