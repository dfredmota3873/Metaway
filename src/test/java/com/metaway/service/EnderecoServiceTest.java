package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Endereco;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.EnderecoRepository;
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
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;
    private Cliente cliente;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        cliente = new Cliente();
        cliente.setId(UUID.randomUUID());

        endereco = new Endereco();
        endereco.setId(id);
        endereco.setCidade("São Paulo");
        endereco.setBairro("Centro");
        endereco.setLogradouro("Rua A");
        endereco.setCliente(cliente);
        endereco.setTag("Residencial");
    }

    @Test
    void cadastrar_DeveSalvarEndereco() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco resultado = enderecoService.cadastrar(endereco);

        assertNotNull(resultado);
        assertEquals(endereco.getCidade(), resultado.getCidade());
        verify(clienteRepository, times(1)).findById(cliente.getId());
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    void cadastrar_DeveLancarExcecao_QuandoClienteNaoExistir() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enderecoService.cadastrar(endereco));
        verify(clienteRepository, times(1)).findById(cliente.getId());
    }

    @Test
    void buscarTodos_DeveRetornarListaDeEnderecos() {
        when(enderecoRepository.findAll()).thenReturn(List.of(endereco));

        List<Endereco> resultado = enderecoService.buscarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarEndereco_QuandoIdExistir() {
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco resultado = enderecoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(endereco.getId(), resultado.getId());
        verify(enderecoRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enderecoService.buscarPorId(id));
        verify(enderecoRepository, times(1)).findById(id);
    }

    @Test
    void atualizar_DeveAlterarEndereco() {
        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setCidade("Rio de Janeiro");
        enderecoAtualizado.setBairro("Copacabana");
        enderecoAtualizado.setLogradouro("Rua B");
        enderecoAtualizado.setTag("Comercial");
        enderecoAtualizado.setCliente(cliente);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoAtualizado);

        Endereco resultado = enderecoService.atualizar(id, enderecoAtualizado);

        assertNotNull(resultado);
        assertEquals("Rio de Janeiro", resultado.getCidade());
        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        Endereco enderecoAtualizado = new Endereco();
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enderecoService.atualizar(id, enderecoAtualizado));
        verify(enderecoRepository, times(1)).findById(id);
    }

    @Test
    void deletar_DeveRemoverEndereco_QuandoIdExistir() {
        doNothing().when(enderecoRepository).deleteById(id);

        enderecoService.deletar(id);

        verify(enderecoRepository, times(1)).deleteById(id);
    }
}
