package com.metaway.service;

import com.metaway.model.Raca;
import com.metaway.repository.RacaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RacaServiceTest {

    @Mock
    private RacaRepository racaRepository;

    @InjectMocks
    private RacaService racaService;

    private Raca raca;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        raca = new Raca();
        raca.setId(id);
        raca.setDescricao("Raça Teste");
    }

    @Test
    void buscarTodos_DeveRetornarListaDeRacas() {
        List<Raca> racas = Arrays.asList(raca, new Raca());

        when(racaRepository.findAll()).thenReturn(racas);

        List<Raca> resultado = racaService.buscarTodos();

        assertEquals(2, resultado.size());
        verify(racaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_QuandoExiste_DeveRetornarRaca() {
        when(racaRepository.findById(id)).thenReturn(Optional.of(raca));

        Raca resultado = racaService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(raca.getDescricao(), resultado.getDescricao());
        verify(racaRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_QuandoNaoExiste_DeveLancarNotFoundException() {
        when(racaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> racaService.buscarPorId(id));

        verify(racaRepository, times(1)).findById(id);
    }

    @Test
    void cadastrar_DeveSalvarENaoretornaRaca() {
        when(racaRepository.save(raca)).thenReturn(raca);

        Raca resultado = racaService.cadastrar(raca);

        assertNotNull(resultado);
        assertEquals(raca.getDescricao(), resultado.getDescricao());
        verify(racaRepository, times(1)).save(raca);
    }

    @Test
    void deletar_DeveChamarRepository() {
        doNothing().when(racaRepository).deleteById(id);

        racaService.deletar(id);

        verify(racaRepository, times(1)).deleteById(id);
    }

    @Test
    void atualizar_QuandoExiste_DeveAtualizarRaca() {
        Raca racaAtualizada = new Raca();
        racaAtualizada.setDescricao("Nova Descrição");

        when(racaRepository.findById(id)).thenReturn(Optional.of(raca));
        when(racaRepository.save(any(Raca.class))).thenReturn(racaAtualizada);

        Raca resultado = racaService.atualizar(id, racaAtualizada);

        assertNotNull(resultado);
        assertEquals("Nova Descrição", resultado.getDescricao());
        verify(racaRepository, times(1)).findById(id);
        verify(racaRepository, times(1)).save(any(Raca.class));
    }

    @Test
    void atualizar_QuandoNaoExiste_DeveLancarNotFoundException() {
        when(racaRepository.findById(id)).thenReturn(Optional.empty());

        Raca racaAtualizada = new Raca();
        racaAtualizada.setDescricao("Nova Descrição");

        assertThrows(NotFoundException.class, () -> racaService.atualizar(id, racaAtualizada));

        verify(racaRepository, times(1)).findById(id);
        verify(racaRepository, never()).save(any(Raca.class));
    }
}
