package hse.kpo.homework2.domain.events;

public class DomainEventPublisher {
    public static void publish(DomainEvent event) {
        System.out.println(event.toString());
    }
}
