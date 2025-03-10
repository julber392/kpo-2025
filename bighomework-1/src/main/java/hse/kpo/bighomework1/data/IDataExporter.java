package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.Map;

public interface IDataExporter {
    void exportData(Map<Integer, BankAccount> data, String filePath)throws IOException;
}
