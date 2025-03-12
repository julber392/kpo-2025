package hse.kpo.bighomework1.data.exporter;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.Exportable;

import java.io.IOException;
import java.util.Map;

public interface IDataManagerExporter {
    void exportData(ReportFormat format, Map<Integer,? extends Exportable> data,
                    String filePath) throws IOException;
}
