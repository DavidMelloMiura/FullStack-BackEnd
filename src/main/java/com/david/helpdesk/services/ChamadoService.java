package com.david.helpdesk.services;

import com.david.helpdesk.domain.Chamado;
import com.david.helpdesk.repositories.ChamadoRepository;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado " + id));
    }
}
