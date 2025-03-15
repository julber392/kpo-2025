package hse.kpo.bighomework1.facades;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class BankAccountFacade {
    final BankAccountStorage bankAccountStorage;
    final BankAccountFactory bankAccountFactory;
    @Autowired
    public BankAccountFacade(BankAccountStorage bankAccountStorage, BankAccountFactory bankAccountFactory) {
        this.bankAccountStorage = bankAccountStorage;
        this.bankAccountFactory = bankAccountFactory;
    }

    public void create(String name){
        bankAccountStorage.add(bankAccountFactory.createAccount(name,0));
    }
    public BankAccountStorage get(){
        return bankAccountStorage;
    }
    public void delete(int id){
        bankAccountStorage.delete(id);
    }
    public void update(int id, String newName, Integer newBalance){
        bankAccountStorage.add(bankAccountFactory.createAccount(id,newName,newBalance));
    }
}
