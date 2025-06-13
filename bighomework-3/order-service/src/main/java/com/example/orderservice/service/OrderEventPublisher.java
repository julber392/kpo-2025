package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderEvent;
import com.example.orderservice.domain.EventType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.order}")
    private String orderRoutingKey;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreatedEvent(Order order) {
        OrderEvent event = OrderEvent.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .amount(order.getAmount())
                .eventType(EventType.ORDER_CREATED)
                .build();

        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, event);
    }
}