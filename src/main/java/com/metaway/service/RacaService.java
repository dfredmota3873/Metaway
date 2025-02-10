package com.metaway.service;

import com.metaway.model.Raca;
import com.metaway.repository.RacaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RacaService {

    private final RacaRepository racaRepository;

    public List<Raca> buscarTodos() {

        return racaRepository.findAll();
    }

    public Raca buscarPorId(UUID id) {
        return racaRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe raça com o id : " + id));
    }

    public Raca cadastrar(Raca raca) {
        return racaRepository.save(raca);
    }

    public void deletar(UUID id) {
        racaRepository.deleteById(id);
    }

    public Raca atualizar(UUID id, Raca raca) {
        Raca racaDB = racaRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe raça com o id : " + id));

        racaDB.setDescricao(raca.getDescricao());

        return racaRepository.save(racaDB);
    }
}
