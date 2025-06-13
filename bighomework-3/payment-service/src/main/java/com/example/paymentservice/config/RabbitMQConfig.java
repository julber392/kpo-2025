package com.example.paymentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.order.name}")
    private String orderQueueName;

    @Value("${rabbitmq.queue.payment.status.name}")
    private String paymentStatusQueueName;

    @Value("${rabbitmq.routing.key.order}")
    private String orderRoutingKey;

    @Value("${rabbitmq.routing.key.payment.status}")
    private String paymentStatusRoutingKey;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueueName);
    }

    @Bean
    public Queue paymentStatusQueue() {
        return new Queue(paymentStatusQueueName);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder
                .bind(orderQueue())
                .to(exchange())
                .with(orderRoutingKey);
    }

    @Bean
    public Binding paymentStatusBinding() {
        return BindingBuilder
                .bind(paymentStatusQueue())
                .to(exchange())
                .with(paymentStatusRoutingKey);
    }
}