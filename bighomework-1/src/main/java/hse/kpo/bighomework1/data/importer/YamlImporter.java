package hse.kpo.bighomework1.data.importer;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Map;

public class YamlImporter extends DataImporter {
    @Override
    protected String readFile(String filePath) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
    }

    @Override
    protected Map<String, String> parseData(String data) {
        Yaml yaml = new Yaml();
        return yaml.load(data);
    }

    @Override
    protected void processData(Map<String, String> parsedData) {
        System.out.println("Обработан YAML: " + parsedData);
    }
}
