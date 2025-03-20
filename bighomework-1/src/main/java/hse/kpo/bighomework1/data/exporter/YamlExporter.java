package hse.kpo.bighomework1.data.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.data.ReportFormat;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hse.kpo.bighomework1.entity.Exportable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component
public class YamlExporter implements IDataExporter{
    private final ObjectMapper yamlMapper = new YAMLMapper();
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {
        Map<Integer, Map<String, String>> exportedData = new HashMap<>();

        data.forEach((key, value) -> exportedData.put(key, value.export(getFormat())));

        File file = new File(filePath + ".yaml");

        yamlMapper.writeValue(file, exportedData);
    }

    @Override
    public ReportFormat getFormat() {
        return ReportFormat.YAML;
    }
}
