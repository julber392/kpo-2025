package hse.kpo.bighomework1.data.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CSVImporter extends DataImporter {
    @Override
    protected String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    @Override
    protected Map<String, String> parseData(String data) {
        Map<String, String> result = new LinkedHashMap<>();
        String[] lines = data.split("\n");
        if (lines.length < 2) return result;

        String[] headers = lines[0].split(",");
        String keyHeader = headers[0];

        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            if (values.length < headers.length) continue;

            String key = values[0];
            Map<String, String> rowMap = new LinkedHashMap<>();
            for (int j = 1; j < headers.length; j++) {
                rowMap.put(headers[j], values[j]);
            }

            result.put(key, rowMap.toString());
        }
        return result;
    }

    @Override
    protected void processData(Map<String, String> parsedData) {
        System.out.println("Обработан CSV: " + parsedData);
    }
}