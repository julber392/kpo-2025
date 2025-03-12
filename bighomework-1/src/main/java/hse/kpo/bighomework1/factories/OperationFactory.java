package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OperationFactory {


    public Operation createOperation(Integer id, Category category, BankAccount bankAccount,
                                     Integer amount, String date, String description, CategoryType type) {
        if (id == null || category == null || bankAccount == null || amount == null || date == null || description == null || type == null) {
            throw new IllegalArgumentException("None of the Operation fields can be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Operation amount must be greater than zero");
        }
        return new Operation(id, category, bankAccount, amount, date, description, type);
    }
}
