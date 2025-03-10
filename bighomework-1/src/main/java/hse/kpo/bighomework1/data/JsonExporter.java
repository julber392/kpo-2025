package hse.kpo.bighomework1.data;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Operation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonExporter implements IDataExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void exportBankAccount(Map<Integer, BankAccount> data, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    @Override
    public void exportCategory(Map<Integer, Category> data, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    @Override
    public void exportOperation(Map<Integer, Operation> data, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}
