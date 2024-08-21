package com.david.helpdesk.domain;

import com.david.helpdesk.domain.dtos.TecnicoDTO;
import com.david.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 1L;

    //Se não colocar JsonIgnore essa anotação, entra em um loop infinito - Serialização
    // Protege contra Serialização
    @JsonIgnore
    // Um tecnico para varios chamados
    @OneToMany(mappedBy = "tecnico") //La do outro lado (Class-Chamado) esta sendo mapeado pelo tecnico
    private List<Chamado> chamado = new ArrayList<>();


    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Tecnico(TecnicoDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<Chamado> getChamado() {
        return chamado;
    }

    public void setChamado(List<Chamado> chamado) {
        this.chamado = chamado;
    }
}
