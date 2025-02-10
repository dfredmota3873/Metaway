package com.metaway.service;

import com.metaway.model.Cliente;
import com.metaway.model.Endereco;
import com.metaway.repository.ClienteRepository;
import com.metaway.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;

    public Endereco cadastrar(Endereco endereco) {

        Cliente cliente = clienteRepository.findById(endereco.getCliente().getId())
                .orElseThrow(() -> new NotFoundException("Não existe cliente com o id : " + endereco.getCliente().getId()));

        endereco.setCliente(cliente);

         return enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(UUID id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe endereço com o id : " + id));
    }

    public Endereco atualizar(UUID id, Endereco endereco) {

        Endereco enderecoDB = buscarPorId(id);
        enderecoDB.setCidade(endereco.getCidade());
        enderecoDB.setBairro(endereco.getBairro());
        enderecoDB.setCidade(endereco.getCidade());
        enderecoDB.setLogradouro(endereco.getLogradouro());
        enderecoDB.setCliente(endereco.getCliente());
        enderecoDB.setTag(endereco.getTag());

        return enderecoRepository.save(enderecoDB);

    }
    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }
}
