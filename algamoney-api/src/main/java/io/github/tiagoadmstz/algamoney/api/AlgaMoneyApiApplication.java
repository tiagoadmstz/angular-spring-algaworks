package io.github.tiagoadmstz.algamoney.api;

import io.github.tiagoadmstz.algamoney.api.properties.config.AlgamoneyApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperties.class)
public class AlgaMoneyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgaMoneyApiApplication.class, args);
    }

}