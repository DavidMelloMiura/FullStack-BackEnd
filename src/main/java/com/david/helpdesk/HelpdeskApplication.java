package com.david.helpdesk;

import com.david.helpdesk.domain.Chamado;
import com.david.helpdesk.domain.Cliente;
import com.david.helpdesk.domain.Tecnico;
import com.david.helpdesk.domain.enums.Perfil;
import com.david.helpdesk.domain.enums.Prioridade;
import com.david.helpdesk.domain.enums.Status;
import com.david.helpdesk.repositories.ChamadoRepository;
import com.david.helpdesk.repositories.ClienteRepository;
import com.david.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication {


    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }


}
