package com.david.helpdesk.services;

import com.david.helpdesk.domain.Chamado;
import com.david.helpdesk.domain.Cliente;
import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.dtos.ChamadoDTO;
import com.david.helpdesk.domain.enums.Prioridade;
import com.david.helpdesk.domain.enums.Status;
import com.david.helpdesk.repositories.ChamadoRepository;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado " + id));
    }

//    public Chamado create(ChamadoDTO chamadoDTO) {
//        Chamado newChamado = new Chamado(chamadoDTO);
//        return chamadoRepository.save(newChamado);
//    }


//    Criar
    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO)); //chama metodo newChamado abaixo
    }

//    Criar e Atualizar =
    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(obj.getPrioridade());
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;

    }


    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldChamado = findById(id);
        oldChamado = newChamado(chamadoDTO);
//        oldChamado.setPrioridade(chamadoDTO.getPrioridade());
        return chamadoRepository.save(oldChamado);
    }
}
