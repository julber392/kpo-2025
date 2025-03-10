package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.Map;

public class CSVHandler implements IDataHandler {
    @Override
    public void exportData(Map<Integer, BankAccount> data, String filePath) throws IOException {

    }

    @Override
    public Map<Integer, BankAccount> importData(String filePath) throws IOException{
        return null;
    }
}
