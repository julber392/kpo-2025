package hse.kpo.bighomework1.data;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonHandler implements IDataHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void exportData(Map<Integer, BankAccount> data, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    @Override
    public Map<Integer, BankAccount> importData(String filePath)throws IOException {
        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory()
                .constructMapType(HashMap.class, Integer.class, BankAccount.class));
    }
}
