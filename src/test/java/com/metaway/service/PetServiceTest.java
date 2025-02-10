package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Pet;
import com.metaway.model.Raca;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.PetRepository;
import com.metaway.repository.RacaRepository;
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
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RacaRepository racaRepository;

    @InjectMocks
    private PetService petService;

    private Pet pet;
    private Cliente cliente;
    private Raca raca;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        cliente = new Cliente();
        cliente.setId(UUID.randomUUID());

        raca = new Raca();
        raca.setId(UUID.randomUUID());

        pet = new Pet();
        pet.setId(id);
        pet.setNome("Rex");
        pet.setCliente(cliente);
        pet.setRaca(raca);
    }

    @Test
    void cadastrar_DeveSalvarEPetRetornado() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        when(racaRepository.findById(raca.getId())).thenReturn(Optional.of(raca));
        when(petRepository.save(pet)).thenReturn(pet);

        Pet resultado = petService.cadastrar(pet);

        assertNotNull(resultado);
        assertEquals(pet.getNome(), resultado.getNome());
        verify(clienteRepository, times(1)).findById(cliente.getId());
        verify(racaRepository, times(1)).findById(raca.getId());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void cadastrar_DeveLancarExcecao_QuandoClienteNaoExistir() {
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.cadastrar(pet));
        verify(clienteRepository, times(1)).findById(cliente.getId());
    }

    @Test
    void buscarTodos_DeveRetornarListaDePets() {
        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<Pet> resultado = petService.buscarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarPet_QuandoIdExistir() {
        when(petRepository.findById(id)).thenReturn(Optional.of(pet));

        Pet resultado = petService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(pet.getId(), resultado.getId());
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExistir() {
        when(petRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.buscarPorId(id));
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void atualizar_DeveAlterarEPetRetornado() {
        Pet petAtualizado = new Pet();
        petAtualizado.setNome("Buddy");
        petAtualizado.setCliente(cliente);
        petAtualizado.setRaca(raca);

        when(petRepository.findById(id)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(petAtualizado);

        Pet resultado = petService.atualizar(id, petAtualizado);

        assertNotNull(resultado);
        assertEquals("Buddy", resultado.getNome());
        verify(petRepository, times(1)).findById(id);
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoIdNaoExistir() {
        Pet petAtualizado = new Pet();
        when(petRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.atualizar(id, petAtualizado));
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void deletar_DeveRemoverPet_QuandoIdExistir() {
        doNothing().when(petRepository).deleteById(id);

        petService.deletar(id);

        verify(petRepository, times(1)).deleteById(id);
    }
}
