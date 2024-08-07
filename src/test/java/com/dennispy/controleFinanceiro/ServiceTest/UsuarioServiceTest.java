package com.dennispy.controleFinanceiro.ServiceTest;

import com.dennispy.controleFinanceiro.domain.usuario.model.Usuario;
import com.dennispy.controleFinanceiro.domain.usuario.service.UsuarioService;
import com.dennispy.controleFinanceiro.exception.ErroAutenticacao;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;
import com.dennispy.controleFinanceiro.domain.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    UsuarioService service;
    @MockBean
    UsuarioRepository repository;

    @Test
    public void deveSalvarUmUsuario(){
        //cenario
        Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder()
                .id(1)
                .nome("Dennis")
                .email("email@email.com")
                .senha("senha")
                .build();

        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        //acao
        Usuario usuarioSalvo = service.salvarUsuario(new Usuario());

        //verificacao
        Assertions.assertNotNull(usuarioSalvo, "The saved user should not be null");
        Assertions.assertEquals(1, usuarioSalvo.getId(), "The user ID should be 1");
        Assertions.assertEquals("Dennis", usuarioSalvo.getNome(), "The user name should be 'nome'");
        Assertions.assertEquals("email@email.com", usuarioSalvo.getEmail(), "The user email should be 'email@email.com'");
        Assertions.assertEquals("senha", usuarioSalvo.getSenha(), "The user password should be 'senha'");

    }

    @Test
    public void naoDeveSalvarUsuarioComEmailJaCadastrado(){
        //cenario
        String email = "email@email.com";
        Usuario usuario = Usuario.builder().email(email).build();
        Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);

        //acao
        assertThrows(RegraNegocioException.class, () -> {
            service.salvarUsuario(usuario);
        });

        //verificacao
        Mockito.verify(repository, Mockito.never()).save(usuario);
    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso(){
        //cenario
        String email = "email@email.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(11).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        //acao
        Usuario result = service.autenticar(email, senha);

        //verificacao
        Assertions.assertNotNull(result, "O usuário autenticado não deve ser nulo");
    }

    @Test
    public void deveLancarErrorQuandoNaoEncontrarUsuarioCadastradoComEmailInformado(){
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        // ação e verificação
        ErroAutenticacao exception = assertThrows(ErroAutenticacao.class, () -> {
            service.autenticar("email@email.com", "senha");
        }, "Deveria lançar ErroAutenticacao");

        Assertions.assertEquals("Usuário não encontrado para o email informado!", exception.getMessage(), "Mensagem de erro diferente do esperado");

        // Verifica se o método findByEmail foi chamado com qualquer string
        Mockito.verify(repository).findByEmail(Mockito.anyString());
    }

    @Test
    public void deveLancarErrorQuandoSenhaNaoBater(){
        // cenário
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        // ação e verificação
        ErroAutenticacao exception = assertThrows(ErroAutenticacao.class, () -> {
            service.autenticar("email@email.com", "123");
        }, "Deveria lançar ErroAutenticacao");

        Assertions.assertEquals("Senha inválida.", exception.getMessage(), "Mensagem de erro diferente do esperado");

        // Verifica se o método findByEmail foi chamado com qualquer string
        Mockito.verify(repository).findByEmail(Mockito.anyString());

    }

    @Test
    public void deveValidarQuandoAUmEmail(){

        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

        service.validarEmail("email@email.com");
    }
}
