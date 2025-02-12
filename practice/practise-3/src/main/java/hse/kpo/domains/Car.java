package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * класс для работы с машинами
 */
@ToString
public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     * внутри метода просто вызываем соответствующий метод двигателя
     * @param customer
     * @return
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer);
    }
}
