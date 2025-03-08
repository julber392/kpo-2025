package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Operation;

public class OperationFactory {
    int id=0;
    Operation createOperation(int id, Category category,
                              BankAccount bank_account,
                              int amount, String date,
                              String description,
                              String type){
        return new Operation(id++,category,bank_account,
                amount,date,description,type);
    }
}
