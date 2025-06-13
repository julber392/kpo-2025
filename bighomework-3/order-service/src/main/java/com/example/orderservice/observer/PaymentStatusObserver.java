package com.example.orderservice.observer;

import com.example.orderservice.domain.OrderEvent;
import com.example.orderservice.domain.OrderStatus;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentStatusObserver {

    private final OrderService orderService;

    @RabbitListener(queues = "${rabbitmq.queue.payment.status.name}")
    public void handlePaymentEvent(OrderEvent event) {
        log.info("Received payment status event: {}", event);

        switch (event.getEventType()) {
            case PAYMENT_SUCCESS:
                orderService.updateOrderStatus(event.getOrderId(), OrderStatus.FINISHED);
                break;
            case PAYMENT_FAILED:
                orderService.updateOrderStatus(event.getOrderId(), OrderStatus.CANCELLED);
                break;
            default:
                log.warn("Unknown event type: {}", event.getEventType());
        }
    }
}