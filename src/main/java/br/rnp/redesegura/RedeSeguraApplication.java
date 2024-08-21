package br.rnp.redesegura;


import br.rnp.redesegura.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedeSeguraApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RedeSeguraApplication.class);
		application.addListeners(new SwaggerConfig());
		application.run(args);
	}

}
