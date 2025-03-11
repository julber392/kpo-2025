package hse.kpo.bighomework1.data.exporter;

import java.io.IOException;

public interface IDataManagerExporter {
    void exportData(IDataExporter toExport, String filePath) throws IOException;
}
