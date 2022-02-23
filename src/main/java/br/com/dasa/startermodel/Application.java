package br.com.dasa.startermodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dasa.annotations.EnableCommonApiConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCommonApiConfig
@OpenAPIDefinition(info = @Info(title = "Starter Model", version = "1.0", description = "Applicaion starter model"))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}