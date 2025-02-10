package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Contato;
import com.metaway.model.enums.TipoContato;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.ContatoRepository;
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
class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ContatoService contatoService;

    private Contato contato;
    private Cliente cliente;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        
        contato = new Contato();
        contato.setId(id);
        contato.setTipoContato(TipoContato.EMAIL);
        contato.setValor("teste@email.com");
        contato.setCliente(cliente);
        contato.setTag("Pessoal");
    }

    @Test
    void buscarTodos_DeveRetornarListaDeContatos() {
        when(contatoRepository.findAll()).thenReturn(List.of(contato));

        List<Contato> resultado = contatoService.buscarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(contatoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarContato_QuandoIdExistir() {
        when(contatoRepository.findById(id)).thenReturn(Optional.of(contato));

        Contato resultado = contatoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(contato.getId(), resultado.getId());
        verify(contatoRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(contatoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> contatoService.buscarPorId(id));
        verify(contatoRepository, times(1)).findById(id);
    }

    @Test
    void cadastrar_DeveSalvarContato() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        when(contatoRepository.save(contato)).thenReturn(contato);

        Contato resultado = contatoService.cadastrar(contato);

        assertNotNull(resultado);
        assertEquals(contato.getValor(), resultado.getValor());
        verify(clienteRepository, times(1)).findById(cliente.getId());
        verify(contatoRepository, times(1)).save(contato);
    }

    @Test
    void cadastrar_DeveLancarExcecao_QuandoClienteNaoExistir() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
        
        assertThrows(NotFoundException.class, () -> contatoService.cadastrar(contato));
        verify(clienteRepository, times(1)).findById(cliente.getId());
    }

    @Test
    void atualizar_DeveAlterarContato() {
        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setTipoContato(TipoContato.TELEFONE);
        contatoAtualizado.setValor("(11) 99999-9999");
        contatoAtualizado.setCliente(cliente);
        contatoAtualizado.setTag("Trabalho");

        when(contatoRepository.findById(id)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contatoAtualizado);

        Contato resultado = contatoService.atualizar(id, contatoAtualizado);

        assertNotNull(resultado);
        assertEquals("TELEFONE", resultado.getTipoContato().name());
        verify(contatoRepository, times(1)).findById(id);
        verify(contatoRepository, times(1)).save(any(Contato.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        Contato contatoAtualizado = new Contato();
        when(contatoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> contatoService.atualizar(id, contatoAtualizado));
        verify(contatoRepository, times(1)).findById(id);
    }

    @Test
    void deletar_DeveRemoverContato_QuandoIdExistir() {
        doNothing().when(contatoRepository).deleteById(id);

        contatoService.deletar(id);

        verify(contatoRepository, times(1)).deleteById(id);
    }
}
