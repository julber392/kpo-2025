package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.cars.CarProvider;
import hse.kpo.interfaces.CustomerProvider;
import hse.kpo.observers.SalesAspect;
import java.util.ArrayList;
import java.util.Objects;

import hse.kpo.observers.Sales;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Сервис продажи машин.
 */
@Sales
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCarService {

    private final CarProvider carProvider;

    private final CustomerProvider customerProvider;
    final List<SalesObserver> observers = new ArrayList<>();
    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }
    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }
    /**
     * Метод продажи машин
     */
    @Sales
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                    } else {
                        log.warn("No car in CarService");
                    }
                });
    }
}