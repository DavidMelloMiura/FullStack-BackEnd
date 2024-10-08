package com.david.helpdesk.domain;

import com.david.helpdesk.domain.dtos.ChamadoDTO;
import com.david.helpdesk.domain.enums.Prioridade;
import com.david.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    //Um tecnico pode ter varios chamados - Ou seja Many to One
    @ManyToOne
    @JoinColumn(name ="tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name ="cliente_id")
    private Cliente cliente;


    public Chamado() {
        super();
    }


    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        super();
    }

    public Chamado(Integer id, LocalDate dataAbertura, LocalDate dataFechamento, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }


//        public Chamado(ChamadoDTO obj) {
//        this.id = obj.getId();
//        this.prioridade = obj.getPrioridade();
//        this.status = obj.getStatus();
//        this.titulo = obj.getTitulo();
//        this.observacoes = obj.getObservacoes();
//        this.tecnico = obj.getTecnico();
//        this.cliente = obj.getCliente();
//    }



    public Chamado(ChamadoDTO obj) {
        super();
        this.id = obj.getId();
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }


    


//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public LocalDate getDataAbertura() {
//        return dataAbertura;
//    }
//
//    public void setDataAbertura(LocalDate dataAbertura) {
//        this.dataAbertura = dataAbertura;
//    }
//
//    public LocalDate getDataFechamento() {
//        return dataFechamento;
//    }
//
//    public void setDataFechamento(LocalDate dataFechamento) {
//        this.dataFechamento = dataFechamento;
//    }
//
//    public Integer getPrioridade() {
//        return prioridade;
//    }
//
//    public void setPrioridade(Integer prioridade) {
//        this.prioridade = prioridade;
//    }
//



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id, chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
}
