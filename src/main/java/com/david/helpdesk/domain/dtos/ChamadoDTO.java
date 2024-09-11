package com.david.helpdesk.domain.dtos;


import com.david.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ChamadoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8063268576795438687L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String observacoes;
    private Integer tecnico; //Integer pq quero pegar apenas o id da classe Tecnico
    private Integer cliente; //Integer pq quero pegar apenas o id da classe Cliente
    private String nomeTecnico;
    private String nomeCliente;

//    private Tecnico tecnico;
//    private Cliente cliente;


    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getId();
        this.cliente = obj.getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }
}
