package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.BankAccount;

public class BankAccountFactory {
    int id=0;
    public BankAccount createAccount(String name){
        return new BankAccount(id++,name,0);
    }
}
