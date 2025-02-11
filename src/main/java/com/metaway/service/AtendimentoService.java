package com.metaway.service;

import com.metaway.model.Atendimento;
import com.metaway.model.Pet;
import com.metaway.repository.AtendimentoRepository;
import com.metaway.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    private final PetRepository petRepository;

    public List<Atendimento> buscarTodos() {
        return atendimentoRepository.findAll();
    }

    public Atendimento buscarPorId(UUID id) {
        return atendimentoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Não existe atendimento com o id : " + id));
    }

    public Atendimento cadastrar(Atendimento atendimento) {
        Pet pet = petRepository.findById(atendimento.getPet().getId())
                .orElseThrow(() -> new NotFoundException("Não existe atendimento com o id : " + atendimento.getPet().getId()));

        atendimento.setPet(pet);

        return atendimentoRepository.save(atendimento);
    }

    public void deletar(UUID id) {
        atendimentoRepository.deleteById(id);
    }

    public Atendimento atualizar(UUID id,Atendimento atendimento){
        Atendimento atendimentoDB = atendimentoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Não existe atendimento com o id : " + id));

        atendimentoDB.setPet(atendimento.getPet());
        atendimentoDB.setData(atendimento.getData());
        atendimentoDB.setDescricao(atendimento.getDescricao());
        atendimentoDB.setValor(atendimento.getValor());

        return atendimentoRepository.save(atendimentoDB);
    }
}
