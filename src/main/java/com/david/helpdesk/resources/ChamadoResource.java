package com.david.helpdesk.resources;

import com.david.helpdesk.domain.Chamado;
import com.david.helpdesk.domain.Cliente;
import com.david.helpdesk.domain.dtos.ChamadoDTO;
import com.david.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    public ResponseEntity<List<Chamado>> findAll() {
        List<Chamado> list = chamadoService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);

        if(chamado != null) {
            return ResponseEntity.ok().body(new ChamadoDTO(chamado));
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
