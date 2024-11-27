package br.ufac.sgcmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@RestController
@Hidden
@OpenAPIDefinition(
	info = @Info(
		title = "SGCM API",
		version = "1.0",
		description = "API do SGCM"),
	servers = {
		@Server(url = "https://localhost:9000", description = "Ambiente de Desenvolvimento")}
)
@EnableCaching
@EnableScheduling
public class SgcmapiApplication {

	@RequestMapping("/")
	public String exemplo() {
		return "SGCM";
	}

	public static void main(String[] args) {
		SpringApplication.run(SgcmapiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void exemploRestClient(){
		System.out.println("Consultando CEP...");
		var cliente = RestClient.create();
		var resultado = cliente.get()
			.uri("https://brasilapi.com.br/api/cep/v2/69915288")
			.retrieve()
			.body(String.class);
		System.out.println(resultado);
	}

}
