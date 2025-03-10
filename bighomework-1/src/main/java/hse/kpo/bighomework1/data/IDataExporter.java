package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Operation;

import java.io.IOException;
import java.util.Map;

public interface IDataExporter {
    void exportBankAccount(Map<Integer, BankAccount> data, String filePath)throws IOException;
    void exportCategory(Map<Integer, Category> data, String filePath)throws IOException;
    void exportOperation(Map<Integer, Operation> data, String filePath)throws IOException;
}
