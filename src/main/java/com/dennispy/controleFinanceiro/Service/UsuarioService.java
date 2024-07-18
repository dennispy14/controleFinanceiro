package com.dennispy.controleFinanceiro.Service;

import com.dennispy.controleFinanceiro.components.Messages;
import com.dennispy.controleFinanceiro.exception.ErroAutenticacao;
import com.dennispy.controleFinanceiro.exception.RegraNegocioException;
import com.dennispy.controleFinanceiro.model.entity.Usuario;
import com.dennispy.controleFinanceiro.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /*@Autowired
    private final Messages messages;*/

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
