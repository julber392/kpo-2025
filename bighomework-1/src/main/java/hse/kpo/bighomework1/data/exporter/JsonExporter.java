package hse.kpo.bighomework1.data.exporter;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.Exportable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component
public class JsonExporter implements IDataExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {

        Map<Integer, Map<String, String>> exportedData = new HashMap<>();

        data.forEach((key, value) -> exportedData.put(key, value.export(getFormat())));

        File file = new File(filePath + ".json");

        objectMapper.writeValue(file, exportedData);
    }
    @Override
    public ReportFormat getFormat() {
        return ReportFormat.JSON;
    }
}
