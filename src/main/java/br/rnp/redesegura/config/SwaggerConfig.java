package br.rnp.redesegura.config;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Properties;

@Component
public class SwaggerConfig implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(final ApplicationPreparedEvent event) {
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put("springdoc.swagger-ui.path", swaggerPath());
        environment.getPropertySources()
                .addFirst(new PropertiesPropertySource("programmatically", props));
    }

    private String swaggerPath() {
        return "/rede-segura-api";
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/rede-segura-api", "/");
    }

}
