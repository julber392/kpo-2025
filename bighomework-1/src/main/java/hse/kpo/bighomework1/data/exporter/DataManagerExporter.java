package hse.kpo.bighomework1.data.exporter;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Exportable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataManagerExporter implements IDataManagerExporter {

    private final Map<ReportFormat, IDataExporter> dataExporter;

    @Autowired
    public DataManagerExporter(List<IDataExporter> dataExporterList) {
        this.dataExporter = dataExporterList.stream().collect(Collectors.
                toMap(IDataExporter::getFormat, dataExporter -> dataExporter));
    }
    @Override
    public void exportData(ReportFormat format,Map<Integer,? extends Exportable> data,
                           String filePath) throws IOException {
        dataExporter.get(format).export(data,filePath);
    }
}
