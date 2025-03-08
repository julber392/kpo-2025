package hse.kpo.bighomework1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Operation {
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
}
