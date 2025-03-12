package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import org.springframework.stereotype.Component;


@Component
public class BankAccountFactory {
    public BankAccount createAccount(Integer id, String name, Integer balance) {
        if (id == null || balance == null || name.isBlank()) {
            throw new IllegalArgumentException("Category type and name must not be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be zero or greater than zero");
        }
        return new BankAccount(id, name, balance);
    }
}
