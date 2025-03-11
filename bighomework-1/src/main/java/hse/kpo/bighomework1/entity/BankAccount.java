package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BankAccount implements Exportable {
    Integer id;
    String name;
    Integer balance; //Копейки
    public BankAccount() {}
    public BankAccount(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String export(ReportFormat format) {
        return null;
    }
}
