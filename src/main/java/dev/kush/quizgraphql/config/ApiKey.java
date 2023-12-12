package dev.kush.quizgraphql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKey {
    @Value("${api-key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
