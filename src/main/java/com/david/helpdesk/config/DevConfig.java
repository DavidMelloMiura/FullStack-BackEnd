package com.david.helpdesk.config;

import com.david.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.dll-auto}")
    private String value;

    @Bean
    public boolean instanciaDB() {
        if (value.equals("create")) {
            this.dbService.instaciaDB();
        }
        return false;
    }
    /*
    * Quando estiver no perfil de test, vai chamar metodo instanciaDb,
    *  que cria algumas instancias que vão subir no Banco de dados
    * OBS: Para ativar os perfis vai no application properties
    * */

}
