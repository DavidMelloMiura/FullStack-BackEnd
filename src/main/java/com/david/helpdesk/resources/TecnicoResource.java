package com.david.helpdesk.resources;

import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.dtos.TecnicoDTO;
import com.david.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = this.tecnicoService.findById(id);
        if (obj != null) {
            return ResponseEntity.ok().body(new TecnicoDTO(obj));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll(); //Obtema lista de tecnicos do servico
        List<TecnicoDTO> listDTO = list.stream()
                .map(obj -> new TecnicoDTO(obj))
                .collect(Collectors.toList()); //Converte para DTO
        return ResponseEntity.ok().body(listDTO); // Retorna a lista de DTOs
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objTecnicoDTO) {
        Tecnico newTecnico = tecnicoService.create(objTecnicoDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @RequestBody TecnicoDTO objTecnicoDTO) {
        Tecnico obj = tecnicoService.update(id, objTecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*
    * a linha return ResponseEntity.noContent().build();
    * Está retornando uma resposta HTTP 204 para o cliente,
    * indicando que a operação de exclusão foi bem-sucedida
    *  e não há mais conteúdo para retornar.
    * */



}


//    public Tecnico findById(Integer id) {
//        Optional<Tecnico> obj = tecnicoRepository.findById(id);
//        return obj.orElse(null);
//    }

//}
