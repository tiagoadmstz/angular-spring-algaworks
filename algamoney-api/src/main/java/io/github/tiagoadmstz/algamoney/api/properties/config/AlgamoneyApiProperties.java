package io.github.tiagoadmstz.algamoney.api.properties.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperties {

    private String allowOrigin = "http://localhost:8000";
    private final Security security;

    public AlgamoneyApiProperties() {
        this.security = new Security();
    }

    @Getter
    @Setter
    public static class Security {
        private Boolean enableHttps;
    }

}
