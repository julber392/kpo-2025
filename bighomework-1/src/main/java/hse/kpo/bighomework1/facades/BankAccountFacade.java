package hse.kpo.bighomework1.facades;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;

import java.util.List;
import java.util.Map;

public class BankAccountFacade {
    BankAccountStorage bankAccountStorage=new BankAccountStorage();
    BankAccountFactory bankAccountFactory=new BankAccountFactory();
    void create(String name){
        bankAccountFactory.createAccount(name);
    }
    BankAccountStorage get(){
        return bankAccountStorage;
    }
    void delete(int id){
        bankAccountStorage.getStorage().remove(id);

    }

}
