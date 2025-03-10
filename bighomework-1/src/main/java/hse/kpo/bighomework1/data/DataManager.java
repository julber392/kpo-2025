package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private final Map<String, IDataHandler> handlers = new HashMap<>();

    public DataManager() {
        handlers.put("csv", new CSVHandler());
        handlers.put("json", new JsonHandler());
        handlers.put("yaml", new YamlHandler());
    }

    public void exportData(Map<Integer, BankAccount> data,
                           String filePath,
                           String format) throws IOException {
        IDataHandler handler = handlers.get(format);
        if (handler != null) {
            handler.exportData(data, filePath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public Map<Integer, BankAccount> importData(String filePath,
                                                String format) throws IOException {
        IDataHandler handler = handlers.get(format);
        if (handler != null) {
            return handler.importData(filePath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public void printerData(Map<Integer,BankAccount> accounts){
        accounts.forEach((id, account) ->
                System.out.println("ID: " + account.getId() + ", Name: " + account.getName() + ", Balance: " + account.getBalance()));
    }
}
