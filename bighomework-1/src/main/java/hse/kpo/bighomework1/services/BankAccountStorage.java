package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.services.interfaces.IStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankAccountStorage implements IStorage<BankAccount> {
    private final Map<Integer,BankAccount> bankAccounts = new HashMap<>();
    @Override
    public Map<Integer,BankAccount> getStorage() {
        return bankAccounts;
    }
    @Override
    public void add(BankAccount account)
    {
        bankAccounts.put(account.getId(),account);
    }

    @Override
    public void delete(int index) {
        bankAccounts.remove(index);
    }
}
