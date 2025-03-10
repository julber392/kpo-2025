package hse.kpo.bighomework1.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import hse.kpo.bighomework1.entity.BankAccount;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVImporter implements IDataImporter {
    @Override
    public Map<Integer, BankAccount> importData(String filePath) throws IOException {
        Map<Integer, BankAccount> data = new HashMap<>();
        try (Reader reader = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> rows = csvReader.readAll();
            for (int i = 1; i < rows.size(); i++) {
                int id = Integer.parseInt(rows.get(i)[0]);
                String name = rows.get(i)[1];
                Integer balance = Integer.parseInt(rows.get(i)[2]);
                data.put(id, new BankAccount(id, name, balance));
            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
