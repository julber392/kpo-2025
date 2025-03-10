package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.Map;

public interface IDataHandler {
    void exportData(Map<Integer, BankAccount> data, String filePath)throws IOException;
    Map<Integer, BankAccount> importData(String filePath)throws IOException;
}
