package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.services.interfaces.IStorage;

import java.util.ArrayList;
import java.util.List;

public class BankAccountStorage implements IStorage<BankAccount> {
    private final List<BankAccount> bankAccounts = new ArrayList<>();
    @Override
    public List<BankAccount> getStorage() {
        return bankAccounts;
    }
    @Override
    public void add(BankAccount account)
    {
        bankAccounts.add(account);
    }

    @Override
    public void delete(int index) {
        bankAccounts.remove(index);
    }
}
