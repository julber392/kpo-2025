package hse.kpo.bighomework1.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.entity.BankAccount;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonImporter implements IDataImporter{
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Map<Integer, BankAccount> importData(String filePath)throws IOException {
        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory()
                .constructMapType(HashMap.class, Integer.class, BankAccount.class));
    }
}
