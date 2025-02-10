package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe cliente com o id : " + id));
    }

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar(UUID id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(UUID id,Cliente cliente){
        Cliente clienteDB = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe cliente com o id : " + id));

        clienteDB.setCpf(cliente.getCpf());
        clienteDB.setNome(cliente.getNome());

        return clienteRepository.save(clienteDB);
    }
}
