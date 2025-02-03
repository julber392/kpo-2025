package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * класс для работы с покупателем
 */
@Getter
@ToString
public class Customer {
    private final String name;

    private final int legPower;
    private final int iq;

    private final int handPower;

    @Setter
    private Car car;

    public Customer(String name, int legPower, int handPower,int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq=iq;
    }
}
