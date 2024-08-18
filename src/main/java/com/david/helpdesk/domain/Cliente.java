package com.david.helpdesk.domain;

import com.david.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")  //La do outro lado (Class-Chamado) esta sendo mapeado pelo tecnico
    private List<Chamado> chamados = new ArrayList<>(); //ArrayList para evitar exceção de ponteiro nulo

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE); // Quando um cliente for instanciado, criado, Ja add um perfil
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
