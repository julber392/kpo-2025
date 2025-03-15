package hse.kpo.bighomework1.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.entity.BankAccount;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonImporter extends DataImporter {
    @Override
    protected String readFile(String filePath) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
    }

    @Override
    protected Map<String, String> parseData(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    protected void processData(Map<String, String> parsedData) {
        System.out.println("Обработан JSON: " + parsedData);
    }
}
