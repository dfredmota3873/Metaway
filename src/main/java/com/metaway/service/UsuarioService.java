package com.metaway.service;

import com.metaway.model.Usuario;
import com.metaway.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe usuário com o id : " + id));
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Usuario usuario, UUID id) {

        Usuario  usuarioDB = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe usuário com o id : " + id));

        usuarioDB.setNome(usuario.getNome());
        usuarioDB.setCpf(usuario.getCpf());
        usuarioDB.setNome(usuario.getNome());
        usuarioDB.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuario);
    }
}
