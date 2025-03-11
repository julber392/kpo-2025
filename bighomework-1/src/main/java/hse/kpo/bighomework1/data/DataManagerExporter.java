package hse.kpo.bighomework1.data;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.services.BankAccountStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManagerExporter implements IDataManagerExporter{
    private final BankAccountStorage bankAccountStorage;

    public DataManagerExporter(BankAccountStorage bankAccountStorage) {
        this.bankAccountStorage=bankAccountStorage;
    }
    @Override
    public void exportData(IDataExporter data,String filePath) throws IOException {
        data.exportBankAccount(bankAccountStorage.getStorage(),filePath);
        /*IDataExporter handler = handlers.get(format);
        if (handler != null) {
            handler.exportData(data, filePath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }*/
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
