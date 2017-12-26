package com.hedvig.assetfrontend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Value("${amqp.hostname}")
    private String hostname;

    @Bean
    public ConnectionFactory amqpConnectionFactory() {
        return new CachingConnectionFactory(hostname);
    }

    @Bean
    public MessageConverter assetCommandMessageConverter(ObjectMapper shopCommandObjectMapper) {
        return new Jackson2JsonMessageConverter(shopCommandObjectMapper);
    }

    @Bean
    public ObjectMapper assetCommandObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

}
