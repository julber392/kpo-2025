package hse.kpo.bighomework1.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.entity.BankAccount;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Operation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YamlExporter implements IDataExporter{
    private final ObjectMapper yamlMapper = new YAMLMapper();
    @Override
    public void exportData(Map<Integer, BankAccount> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }

    @Override
    public void exportBankAccount(Map<Integer, BankAccount> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }

    @Override
    public void exportCategory(Map<Integer, Category> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }

    @Override
    public void exportOperation(Map<Integer, Operation> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }
}
