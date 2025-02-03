package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.domains.LevitateEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * класс который создает машины с ручным двигателем
 */
@Component
public class LevitateCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new LevitateEngine(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}