package com.david.helpdesk.domain.dtos;


import com.david.helpdesk.domain.Chamado;
import com.david.helpdesk.domain.enums.Prioridade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ChamadoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    @NotEmpty(message = "O Campo Status é requerido")
    private Integer status;
    @NotEmpty(message = "O Campo Título é requerido")
    private String titulo;
    @NotEmpty(message = "O Campo Observações é requerido")
    private String observacoes;
    @NotEmpty(message = "O Campo Técnico é requerido")
    private Integer tecnico; //Integer pq quero pegar apenas o id da classe Tecnico
    @NotEmpty(message = "O Campo Cliente é requerido")
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
        this.prioridade = obj.getPrioridade();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getId();
        this.cliente = obj.getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }
}
