package com.david.helpdesk.config;

import com.david.helpdesk.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private DBService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instaciaDB();
    }
    /*
    * Quando estiver no perfil de test, vai chamar metodo instanciaDb,
    *  que cria algumas instancias que vão subir no Banco de dados
    * OBS: Para ativar os perfis vai no application properties
    * */

}
