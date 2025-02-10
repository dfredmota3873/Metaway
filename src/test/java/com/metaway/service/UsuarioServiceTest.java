package com.metaway.service;

import com.metaway.model.Usuario;
import com.metaway.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Teste Usuario");
        usuario.setCpf("123.456.789-00");
        usuario.setSenha("senhaSegura");
    }

    @Test
    void buscarPorId_DeveRetornarUsuario_QuandoIdExistir() {
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.buscarPorId(id);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.buscarPorId(id));
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void buscarTodos_DeveRetornarListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> result = usuarioService.buscarTodos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void cadastrar_DeveSalvarEUsuarioRetornado() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.cadastrar(usuario);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deletar_DeveRemoverUsuario_QuandoIdExistir() {
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deletar(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void atualizar_DeveAlterarEUsuarioRetornado() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Novo Nome");
        usuarioAtualizado.setCpf("987.654.321-00");
        usuarioAtualizado.setSenha("novaSenhaSegura");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        Usuario result = usuarioService.atualizar(usuarioAtualizado, id);

        assertNotNull(result);
        assertEquals("Novo Nome", result.getNome());
        assertEquals("987.654.321-00", result.getCpf());
        assertEquals("novaSenhaSegura", result.getSenha());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        Usuario usuarioAtualizado = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.atualizar(usuarioAtualizado, id));
        verify(usuarioRepository, times(1)).findById(id);
    }
}

