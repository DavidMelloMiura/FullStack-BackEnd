package com.david.helpdesk.services;

import ch.qos.logback.core.net.server.Client;
import com.david.helpdesk.domain.Cliente;
import com.david.helpdesk.domain.dtos.ClienteDTO;
import com.david.helpdesk.repositories.ClienteRepository;
import com.david.helpdesk.services.exception.DataIntegrityViolationException;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado " + id));
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        Cliente newCliente = new Cliente(clienteDTO);
        return clienteRepository.save(newCliente);
    }

    //UPDATE
    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = findById(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getCpf());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setPerfis(clienteDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet()));

        return clienteRepository.save(cliente);
    }

    //DELETE
    public void delete(Integer id) {
        Cliente cliente = findById(id);

        if (cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Esse cliente possui chamado, Não pode ser deletado");
        }

        clienteRepository.delete(cliente);
    }
}
