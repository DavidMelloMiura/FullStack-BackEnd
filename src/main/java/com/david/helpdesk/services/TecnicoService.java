package com.david.helpdesk.services;

import com.david.helpdesk.domain.Pessoa;
import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.dtos.TecnicoDTO;
import com.david.helpdesk.repositories.PessoaRepository;
import com.david.helpdesk.repositories.TecnicoRepository;
import com.david.helpdesk.services.exception.DataIntegrityViolationException;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
//        return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
//      retorna obj ou lança função anonima() Exception
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objTecnicoDTO) {
        objTecnicoDTO.setId(null);
        validaPorCpfEEmail(objTecnicoDTO);
        Tecnico newTecnico = new Tecnico(objTecnicoDTO);
        return tecnicoRepository.save(newTecnico);
    }

    private void validaPorCpfEEmail(TecnicoDTO objTecnicoDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objTecnicoDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objTecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

//        Aproveitando o obj criado acima (Optional<Pessoa> obj)
//        Se não entrar na condição acima (if) significa que passou
//        Então busca por email e verifica se existe
        obj = pessoaRepository.findByEmail(objTecnicoDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objTecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
    }



//    public List<Tecnico> findTecnicoById(Integer id) {
//        return tecnicoRepository.findById(id);
//    }



}
