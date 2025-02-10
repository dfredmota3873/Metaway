package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.repository.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("João Silva");
        cliente.setCpf("123.456.789-00");
    }

    @Test
    void buscarTodos_DeveRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.buscarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarCliente_QuandoIdExistir() {
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(cliente.getId(), resultado.getId());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clienteService.buscarPorId(id));
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void cadastrar_DeveSalvarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.cadastrar(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void atualizar_DeveAlterarCliente() {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Maria Oliveira");
        clienteAtualizado.setCpf("987.654.321-00");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.atualizar(id, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals("Maria Oliveira", resultado.getNome());
        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        Cliente clienteAtualizado = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clienteService.atualizar(id, clienteAtualizado));
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void deletar_DeveRemoverCliente_QuandoIdExistir() {
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.deletar(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }
}
