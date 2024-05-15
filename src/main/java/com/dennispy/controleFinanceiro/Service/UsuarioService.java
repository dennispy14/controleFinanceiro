package com.dennispy.controleFinanceiro.Service;

import com.dennispy.controleFinanceiro.components.Messages;
import com.dennispy.controleFinanceiro.exception.ErroAutenticacao;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;
import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Messages messages;

    public Usuario autenticar(String email, String senha){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(!usuario.isPresent()){
            throw new ErroAutenticacao("Usuário não encontrado para o email informado!")
        } else if(!usuario.get().getSenha().equals(senha)){
            throw new ErroAutenticacao("Senha inválida.");
        }
        return usuario.get();
    }

    public Usuario salvarUsuario(Usuario usuario){
        validarEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }
    public void  validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (!existe){
            throw new RegraNegocioException(messages.get("email.nao-encontrado"));
        }
    }

}
