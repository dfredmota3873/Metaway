package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Contato;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;

    private final ClienteRepository clienteRepository;

    public List<Contato> buscarTodos() {
        return contatoRepository.findAll();
    }

    public Contato buscarPorId(UUID id) {
        return contatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe contato com o id : " + id));
    }

    public Contato cadastrar(Contato contato) {
        Cliente cliente = clienteRepository.findById(contato.getCliente().getId())
                .orElseThrow(() -> new NotFoundException("Não existe cliente com o id : " + contato.getCliente().getId()));

        contato.setCliente(cliente);

        return contatoRepository.save(contato);
    }

    public void deletar(UUID id) {
        contatoRepository.deleteById(id);
    }

    public Contato atualizar(UUID id,Contato cliente){
        Contato contatoDB = contatoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe contato com o id : " + id));

        contatoDB.setTipoContato(cliente.getTipoContato());
        contatoDB.setValor(cliente.getValor());
        contatoDB.setCliente(cliente.getCliente());
        contatoDB.setTag(cliente.getTag());

        return contatoRepository.save(contatoDB);
    }
}
