package com.example.backend.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Getter
@Setter
@Configuration
public class FirebaseConfigurationProperties {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("private_key")
    private String privateKey;

    @JsonProperty("web-api-key")
    @NotBlank(message = "Web API key must be configured")
    private String webApiKey;

    public FirebaseConfigurationProperties() {
        try {
            ClassPathResource resource = new ClassPathResource("firebase-config.json");
            InputStream inputStream = resource.getInputStream();
            Map configMap = objectMapper.readValue(inputStream, Map.class);

            this.privateKey = (String) configMap.get("private_key");
            this.webApiKey = (String) configMap.get("web-api-key");

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Firebase configuration file", e);
        }
    }
}