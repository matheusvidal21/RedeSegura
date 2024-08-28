package br.rnp.redesegura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedeSeguraApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RedeSeguraApplication.class);
		application.run(args);
	}

}
