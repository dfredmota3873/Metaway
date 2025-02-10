package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Pet;
import com.metaway.model.Raca;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.PetRepository;
import com.metaway.repository.RacaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final ClienteRepository clienteRepository;

    private final RacaRepository racaRepository;

    public Pet cadastrar(Pet pet) {

        Cliente cliente = clienteRepository.findById(pet.getCliente().getId())
                .orElseThrow(() -> new NotFoundException("Não existe pet com o id : " + pet.getCliente().getId()));

        Raca raca = racaRepository.findById(pet.getRaca().getId())
                .orElseThrow(() -> new NotFoundException("Não existe pet com o id : " + pet.getCliente().getId()));

        pet.setCliente(cliente);
        pet.setRaca(raca);

        return petRepository.save(pet);
    }

    public List<Pet> buscarTodos() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(UUID id) {
        return petRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe pet com o id : " + id));
    }

    public Pet atualizar(UUID id, Pet pet) {

        Pet petDB = buscarPorId(id);


        petDB.setCliente(pet.getCliente());
        petDB.setRaca(pet.getRaca());
        petDB.setNome(petDB.getNome());
        petDB.setDataNascimento(petDB.getDataNascimento());

        return petRepository.save(petDB);

    }
    public void deletar(UUID id) {
        petRepository.deleteById(id);
    }
}
