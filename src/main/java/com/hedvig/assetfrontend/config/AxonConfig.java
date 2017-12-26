package com.hedvig.assetfrontend.config;

import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Value("${amqp.exchange}")
    private String exchange;

    @Bean
    public EventBus assetEventBus(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

    @Bean
    public FanoutExchange assetExchange() {
        return new FanoutExchange(exchange, true, false);
    }

    @Bean
    public SpringAMQPPublisher assetEventPublisher(EventBus assetEventBus, ConnectionFactory amqpConnectionFactory) {
        SpringAMQPPublisher eventPublisher = new SpringAMQPPublisher(assetEventBus);
        eventPublisher.setConnectionFactory(amqpConnectionFactory);
        eventPublisher.setExchangeName(exchange);
        eventPublisher.start();
        return eventPublisher;
    }

}
