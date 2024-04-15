package com.dennispy.controleFinanceiro.RepositoryTest;

import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmEmail(){

        String email = "usuario@email.com";
        //Cenário
        Usuario usuario = Usuario.builder().nome("usuario").email(email).build();
        repository.save(usuario);
        //Ação/
        boolean result = repository.existsByEmail(email);
        //Verificação
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioiCadastradoComOEmail(){

        //cen[ario
        repository.deleteAll();
        //ação
        boolean result = repository.existsByEmail("usuario@email.com");
        //verificação
        Assertions.assertThat(result).isFalse();
    }
}
