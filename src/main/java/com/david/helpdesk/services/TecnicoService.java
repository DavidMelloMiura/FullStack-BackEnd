package com.david.helpdesk.services;

import com.david.helpdesk.domain.Pessoa;
import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.dtos.TecnicoDTO;
import com.david.helpdesk.repositories.PessoaRepository;
import com.david.helpdesk.repositories.TecnicoRepository;
import com.david.helpdesk.services.exception.DataIntegrityViolationException;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Tecnico update(@Valid Integer id, TecnicoDTO objTecnicoDTO) {
//        objTecnicoDTO.setId(null);
//      Usando o findById do metodo buscar por id - se não achar vai lançar o erro Exception do metodo findById
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objTecnicoDTO);
//        oldObj = new Tecnico(objTecnicoDTO);

        oldObj.setNome(objTecnicoDTO.getNome());
        oldObj.setCpf(objTecnicoDTO.getCpf());
        oldObj.setEmail(objTecnicoDTO.getEmail());
        oldObj.setSenha(objTecnicoDTO.getSenha());
        oldObj.setPerfis(objTecnicoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet()));

        return tecnicoRepository.save(oldObj);
    }

    public void delete(@Valid Integer id) {
        Tecnico tecnico = findById(id); //Verifica se existe esse id

//        Se tecnico tiver chamado > 0
//        Lança Exception de integridade mostrando mesnsagem que tem chamado e não pode deletar
        if (tecnico.getChamado().size() > 0) {
            throw new DataIntegrityViolationException("Esse tecnico possui chamado, Não pode ser deletado");
        }
//      Caso não tenha chamado passa pela condição e executa o deleteById
        tecnicoRepository.delete(tecnico);
    }


    private void validaPorCpfEEmail(TecnicoDTO objTecnicoDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objTecnicoDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objTecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

//        Aproveitando o obj criado acima (Optional<Pessoa> obj)
//        Se não entrar na condição acima (if) significa que passou
//        Então busca por email e verifica se existe
        obj = pessoaRepository.findByEmail(objTecnicoDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objTecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

    }

}