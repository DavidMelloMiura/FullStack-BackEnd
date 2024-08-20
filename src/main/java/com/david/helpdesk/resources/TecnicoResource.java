package com.david.helpdesk.resources;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.dtos.TecnicoDTO;
import com.david.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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


//    public Tecnico findById(Integer id) {
//        Optional<Tecnico> obj = tecnicoRepository.findById(id);
//        return obj.orElse(null);
//    }

}
