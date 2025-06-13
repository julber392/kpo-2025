package com.example.paymentservice.service;

import com.example.paymentservice.domain.OrderEvent;
import com.example.paymentservice.domain.EventType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class PaymentEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.payment.status}")
    private String paymentStatusRoutingKey;

    public PaymentEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPaymentSuccessEvent(Long orderId, Long userId) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setEventType(EventType.PAYMENT_SUCCESS);
        rabbitTemplate.convertAndSend(exchange, paymentStatusRoutingKey, event);
    }

    public void publishPaymentFailedEvent(Long orderId, Long userId) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setEventType(EventType.PAYMENT_FAILED);
        rabbitTemplate.convertAndSend(exchange, paymentStatusRoutingKey, event);
    }
}