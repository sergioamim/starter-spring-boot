package br.com.starter.demo;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.starter.demo.entity.Exemplo;
import br.com.starter.demo.service.ExemploService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
