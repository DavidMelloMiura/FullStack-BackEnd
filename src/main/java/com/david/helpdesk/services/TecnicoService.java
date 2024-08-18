package com.david.helpdesk.services;

import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null);
    }

//    public List<Tecnico> findTecnicoById(Integer id) {
//        return tecnicoRepository.findById(id);
//    }



}
