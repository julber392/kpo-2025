package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Operation implements Exportable{
    Integer id;
    Category category;
    BankAccount bankAccount;
    Integer amount;
    String date;
    String description;
    CategoryType type;

    public Operation(Integer id,
                     Category category,
                     BankAccount bankAccount,
                     Integer amount, String date,
                     String description,
                     CategoryType type) {
        this.id = id;
        this.category = category;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }
    @Override
    public String export(ReportFormat format) {
        return switch (format) {
            case JSON -> String.format("\"%d\":{\"id\":%d,\"type\":\"%s\",\"name\":%s}", id, id, type, name);
            case CSV -> String.format("id,type,name\n%d,%s,%s", id, type, name);
            case YAML -> String.format("%d:\n  id: %d\n  type: %s\n  name: %s",id, id, type, name);
            default -> throw new IllegalArgumentException("Не найден формат для экспорта данных");
        };
    }
}
