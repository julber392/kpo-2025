package hse.kpo.bighomework1.data.importer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class DataImporter {
    // Шаблонный метод
    public final Map<String, String> importData(String filePath) throws IOException {
        String data = readFile(filePath);
        Map<String, String> parsedData = parseData(data);
        processData(parsedData);
        return parsedData;
    }

    protected abstract String readFile(String filePath) throws IOException;
    protected abstract Map<String, String> parseData(String data);
    protected abstract void processData(Map<String, String> parsedData);
}
