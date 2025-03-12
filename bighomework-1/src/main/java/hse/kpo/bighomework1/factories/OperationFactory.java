package hse.kpo.bighomework1.factories;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;

import java.util.concurrent.atomic.AtomicInteger;
public class OperationFactory {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public Operation createOperation(Category category, BankAccount bankAccount,
                                     Integer amount, String date, String description, CategoryType type) {
        return new Operation(idGenerator.getAndIncrement(), category, bankAccount, amount, date, description, type);
    }
}
