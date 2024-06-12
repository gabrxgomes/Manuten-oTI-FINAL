package com.example.ManutencaoTi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManutencaoTiApplication {
	
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // ou "America/Sao_Paulo"
     // formata a data corretamente para salva-la no meu banco de dados.
    }

	public static void main(String[] args) {
		SpringApplication.run(ManutencaoTiApplication.class, args);
	}

}
