package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.ToString;

/**
 * класс для работы с ручным двигателем
 */
@ToString
public class HandEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
