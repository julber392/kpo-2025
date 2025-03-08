package hse.kpo.bighomework1.facades;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;

import java.util.List;

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
        List<BankAccount> bankAccount=bankAccountStorage.getStorage();
        for (int i=0;i<bankAccount.size();i++){
            if (bankAccount.get(i).getId()==id){
                bankAccountStorage.delete(i);
                break;
            }
        }
    }

}
