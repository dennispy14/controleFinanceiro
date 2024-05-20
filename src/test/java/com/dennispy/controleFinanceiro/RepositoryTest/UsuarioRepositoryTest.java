package com.dennispy.controleFinanceiro.RepositoryTest;

import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail(){

        //cenário
        Usuario  usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();        //Cenário
        entityManager.persist(usuario);

        //Ação/
        boolean result = repository.existsByEmail("usuario@email.com");

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

    @Test
    public void devePersistirUmUsuarioBaseDeDados(){
        //cénario
        Usuario usuario = Usuario.builder()
                .nome("usuario")
                .email("usuario@email.com")
                .senha("senha")
                .build();

        //ação
        Usuario usuarioSalvo = repository.save(usuario);

        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail(){
        //cenario
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        //verificacao
        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void deveRetonarVazioBuscarUsuarioPorEmailQuandoNaoExisteNaBase(){
        //cenario

        //verificacao
        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static Usuario criarUsuario(){
        return Usuario
                .builder()
                .nome("usuario")
                .email("usuario@email.com")
                .senha("senha")
                .build();
    }



}
