package com.vishwas.Customer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate getRestTemplate(PropertyConfiguration propertyConfiguration) {
        return new RestTemplateBuilder()
                .rootUri(propertyConfiguration.getLocation())
                .setReadTimeout(Duration.ofMillis(3000))
                .setConnectTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Bean
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name("vishwasTopic").build();
    }
}
