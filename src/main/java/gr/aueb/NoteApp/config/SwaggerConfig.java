package gr.aueb.NoteApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes APP")
                        .version("1.0")
                        .description("Simple notes app with Spring Boot")
                        .contact(new Contact()
                                .name("Grigoris Latsis")
                                .url("https://github.com/g11latsis")
                                .email("g11latsis@gmail.com")));
    }
}
