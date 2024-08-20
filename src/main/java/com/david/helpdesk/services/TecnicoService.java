package com.david.helpdesk.services;

import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.repositories.TecnicoRepository;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
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
//        return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
//      retorna obj ou lança função anonima() Exception
    }

//    public List<Tecnico> findTecnicoById(Integer id) {
//        return tecnicoRepository.findById(id);
//    }



}
