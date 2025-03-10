package hse.kpo.bighomework1.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.entity.BankAccount;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YamlHandler implements IDataHandler{
    private final ObjectMapper yamlMapper = new YAMLMapper();
    @Override
    public void exportData(Map<Integer, BankAccount> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }

    @Override
    public Map<Integer, BankAccount> importData(String filePath) throws IOException {
        return yamlMapper.readValue(new File(filePath), yamlMapper.getTypeFactory()
                .constructMapType(HashMap.class, Integer.class, BankAccount.class));
    }
}
