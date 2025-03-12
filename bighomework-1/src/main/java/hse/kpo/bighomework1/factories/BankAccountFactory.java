package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.BankAccount;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccountFactory {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    public BankAccount createAccount(String name){
        return new BankAccount(idGenerator.getAndIncrement(), name, 0);
    }
}
