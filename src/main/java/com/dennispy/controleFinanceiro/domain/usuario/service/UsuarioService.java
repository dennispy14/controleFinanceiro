package com.dennispy.controleFinanceiro.domain.usuario.service;

import com.dennispy.controleFinanceiro.domain.usuario.model.Usuario;
import com.dennispy.controleFinanceiro.exception.ErroAutenticacao;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;

import com.dennispy.controleFinanceiro.domain.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repository) {
        super();
        this.usuarioRepository = repository;
    }

    public Usuario autenticar(String email, String senha){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isEmpty()){
            throw new ErroAutenticacao("Usuário não encontrado para o email informado!");
        } else if(!usuario.get().getSenha().equals(senha)){
            throw new ErroAutenticacao("Senha inválida.");
        }
        return usuario.get();
    }

    public Usuario salvarUsuario(Usuario usuario){
        validarEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    /*public void  validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (!existe){
            throw new RegraNegocioException("Email não encontrado!");
        }
    }*/

    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if(existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }

}
