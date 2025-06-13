package com.example.paymentservice.service;

import com.example.paymentservice.domain.OrderEvent;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventSubscriber {

    private final PaymentService paymentService;

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void handleOrderEvent(OrderEvent event) {
        log.info("Received order event: {}", event);

        try {
            paymentService.processPayment(
                    event.getOrderId(),
                    event.getUserId(),
                    event.getAmount()
            );
        } catch (Exception e) {
            log.error("Error processing payment for order {}: {}", event.getOrderId(), e.getMessage());
        }
    }
}