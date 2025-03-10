package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManagerExporter {
    private final Map<ReportFormat, IDataExporter> handlers = new HashMap<>();

    public DataManagerExporter() {
        handlers.put(ReportFormat.CSV, new CSVExporter());
        handlers.put(ReportFormat.JSON, new JsonExporter());
        handlers.put(ReportFormat.YAML, new YamlExporter());
    }

    public void exportData(Map<Integer, BankAccount> data,
                           String filePath,
                           ReportFormat format) throws IOException {
        IDataExporter handler = handlers.get(format);
        if (handler != null) {
            handler.exportData(data, filePath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    /*public Map<Integer, BankAccount> importData(String filePath,
                                                ReportFormat format) throws IOException {
        IDataHandler handler = handlers.get(format);
        if (handler != null) {
            return handler.importData(filePath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }*/

    public void printerData(Map<Integer,BankAccount> accounts){
        accounts.forEach((id, account) ->
                System.out.println("ID: " + account.getId() + ", Name: " + account.getName() + ", Balance: " + account.getBalance()));
    }
}
