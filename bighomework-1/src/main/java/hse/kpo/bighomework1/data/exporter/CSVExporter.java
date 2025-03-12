package hse.kpo.bighomework1.data.exporter;

import hse.kpo.bighomework1.data.ReportFormat;
import hse.kpo.bighomework1.entity.BankAccount;
import com.opencsv.CSVWriter;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Exportable;
import hse.kpo.bighomework1.entity.Operation;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVExporter implements IDataExporter {
    @Override
    public void export(Map<Integer, ? extends Exportable> data, String filePath) throws IOException {
        File file = new File(filePath + ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Проверяем, есть ли данные для экспорта
            if (data.isEmpty()) {
                return;
            }

            // Получаем заголовки из первой записи
            Exportable firstEntry = data.values().iterator().next();
            List<String> headers = new ArrayList<>(firstEntry.export(getFormat()).keySet());
            writer.write("ID," + String.join(",", headers));
            writer.newLine();

            // Записываем данные
            for (Map.Entry<Integer, ? extends Exportable> entry : data.entrySet()) {
                List<String> values = new ArrayList<>();
                values.add(entry.getKey().toString()); // Добавляем ID
                Map<String, String> exportedValues = entry.getValue().export(getFormat());
                for (String header : headers) {
                    values.add(exportedValues.getOrDefault(header, "").toString());
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
        }
    }

    @Override
    public ReportFormat getFormat() {
        return ReportFormat.CSV;
    }

}
