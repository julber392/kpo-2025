package hse.kpo.bighomework1.data.exporter;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import com.opencsv.CSVWriter;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Exportable;
import hse.kpo.bighomework1.entity.Operation;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
@Component
public class CSVExporter implements IDataExporter {
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {

        try (Writer writer = new FileWriter(filePath);
             CSVWriter csvWriter = new CSVWriter(writer)) {
            String[] header = {"id", "name", "balance"};
            csvWriter.writeNext(header);
            for (BankAccount account : data.values()) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(account.getId()),
                        account.getName(),
                        String.valueOf(account.getBalance())
                });
            }
        }
    }

    @Override
    public ReportFormat getFormat() {
        return ReportFormat.CSV;
    }
/*@Override
    public void exportCategory(Map<Integer, Category> data, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath);
             CSVWriter csvWriter = new CSVWriter(writer)) {
            String[] header = {"id", "category_type", "name"};
            csvWriter.writeNext(header);
            for (Category category : data.values()) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(category.getId()),
                        String.valueOf(category.getType()),
                        String.valueOf(category.getName())
                });
            }
        }
    }

    @Override
    public void exportOperation(Map<Integer, Operation> data, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath);
             CSVWriter csvWriter = new CSVWriter(writer)) {
            String[] header = {"id", "category_id", "bank_account_id","amount","date","description","category_type"};
            csvWriter.writeNext(header);
            for (Operation operation : data.values()) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(operation.getId()),
                        String.valueOf(operation.getCategory().getId()),
                        String.valueOf(operation.getBankAccount().getId()),
                        String.valueOf(operation.getAmount()),
                        operation.getDate(),
                        operation.getDescription(),
                        String.valueOf(operation.getType())
                });
            }
        }
    }*/
}
