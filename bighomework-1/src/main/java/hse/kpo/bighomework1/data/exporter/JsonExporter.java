package hse.kpo.bighomework1.data.exporter;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.Exportable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component
public class JsonExporter implements IDataExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {
        data.entrySet().forEach(set -> set.getValue().export(getFormat()));
        objectMapper.writeValue(new File(filePath), data);
    }

    @Override
    public ReportFormat getFormat() {
        return ReportFormat.JSON;
    }
}
