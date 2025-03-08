package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.util.Map;

public class JsonHandler implements IDataHandler {
    @Override
    public void exportData(Map<Integer, BankAccount> data, String filePath) {

    }

    @Override
    public Map<Integer, BankAccount> importData(String filePath) {
        return null;
    }
}
