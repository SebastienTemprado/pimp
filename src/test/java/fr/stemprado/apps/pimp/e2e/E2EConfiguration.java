package fr.stemprado.apps.pimp.e2e;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class E2EConfiguration {

	@Bean
    public URI getSiteBase() throws URISyntaxException {
        return new URI("http://localhost:8080/");
    }

}
