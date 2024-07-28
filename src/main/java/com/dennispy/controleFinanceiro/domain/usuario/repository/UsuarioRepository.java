package com.dennispy.controleFinanceiro.domain.usuario.repository;

import com.dennispy.controleFinanceiro.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

}
