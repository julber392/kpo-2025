package hse.kpo.bighomework1.facades;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.factories.OperationFactory;
import hse.kpo.bighomework1.services.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperationFacade {
    OperationStorage operationStorage;
    OperationFactory operationFactory;
    @Autowired
    public OperationFacade(OperationStorage operationStorage, OperationFactory operationFactory) {
        this.operationStorage = operationStorage;
        this.operationFactory = operationFactory;
    }
    public void create(Category category,
                BankAccount bankAccount,
                Integer amount, String date,
                String description,
                CategoryType type){
        operationStorage.add(operationFactory.createOperation(category,bankAccount,amount,date,description,type));
    }
    public OperationStorage get(){
        return operationStorage;
    }
    public void delete(int id){
        operationStorage.delete(id);
    }
    public void update(Integer id,
                Category category,
                BankAccount bankAccount,
                Integer amount, String date,
                String description,
                CategoryType type){
        delete(id);
        operationStorage.add(operationFactory.createOperation(id,category,bankAccount,amount,date,description,type));
    }
}
