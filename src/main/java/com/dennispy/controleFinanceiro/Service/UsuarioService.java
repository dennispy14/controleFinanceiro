package com.dennispy.controleFinanceiro.Service;

import com.dennispy.controleFinanceiro.components.Messages;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;
import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Messages messages;

    public Usuario autenticar(String email, String senha){
        return null;
    }

    public Usuario salvarUsuario(Usuario usuario){
        return null;

    }
    public void  validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (!existe){
            throw new RegraNegocioException(messages.get("email.nao-encontrado"));
        }
    }
}
