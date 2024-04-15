package com.dennispy.controleFinanceiro.ServiceTest;

import com.dennispy.controleFinanceiro.Service.UsuarioService;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;
import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
    @Autowired
    UsuarioService service;
    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveValidarQuandoNaoAUmEmail(){
        //Cenário
        repository.deleteAll();
        //ação

        //verificação
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
    }

    @Test
    public void deveValidarQuandoAUmEmail(){
        String email = "usuario@email.com";
        //Cenário
        Usuario usuario = Usuario.builder().nome("usuario").email(email).build();
        repository.save(usuario);
        //Ação

        //Verificação
        Assertions.assertDoesNotThrow(() -> service.validarEmail(email));
    }
}
