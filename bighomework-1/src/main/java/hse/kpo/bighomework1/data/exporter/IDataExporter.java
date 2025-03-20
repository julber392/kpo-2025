package hse.kpo.bighomework1.data.exporter;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.Exportable;

import java.io.IOException;
import java.util.Map;

public interface IDataExporter {
    void export(Map<Integer, ? extends Exportable> data, String filePath)throws IOException;
    ReportFormat getFormat();
}
