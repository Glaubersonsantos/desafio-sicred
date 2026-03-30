package com.glauberson.desafio_sicred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioSicredApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSicredApplication.class, args);
	}

}
