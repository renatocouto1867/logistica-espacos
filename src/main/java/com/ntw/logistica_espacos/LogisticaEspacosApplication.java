package com.ntw.logistica_espacos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Logística de Espaços", version = "1.0", description = "API para gerenciamento de espaços"))
public class LogisticaEspacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticaEspacosApplication.class, args);
	}
}
