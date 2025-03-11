package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
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
        return switch (format) {
            case JSON -> String.format("\"%d\":{\"id\":%d,\"type\":\"%s\",\"balance\":%d}", id, id, name, balance);
            case CSV -> String.format("id,name,balance\n%d,%s,%d", id, name, balance);
            case YAML -> String.format("%d:\n  id: %d\n  name: %s\n  balance: %d",id, id, name, balance);
            default -> throw new IllegalArgumentException("Не найден формат для экспорта данных");
        };
    }

}
