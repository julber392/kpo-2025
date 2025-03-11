package hse.kpo.bighomework1.data;

import java.io.IOException;

public interface IDataManagerExporter {
    void exportData(IDataExporter to_export, String filePath) throws IOException;
}
