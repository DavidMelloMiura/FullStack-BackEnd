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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Yoshiro", "61085928004", "yoshiro@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Yusuke", "88168950046", "yusuke@email.com","123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Primeiro Chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
