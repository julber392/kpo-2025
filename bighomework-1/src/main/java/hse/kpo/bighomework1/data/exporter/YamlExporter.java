package hse.kpo.bighomework1.data.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.data.ReportFormat;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hse.kpo.bighomework1.entity.Exportable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;
@Component
public class YamlExporter implements IDataExporter{
    private final ObjectMapper yamlMapper = new YAMLMapper();
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {
        yamlMapper.writeValue(new File(filePath), data);
    }

    @Override
    public ReportFormat getFormat() {
        return ReportFormat.YAML;
    }
}
