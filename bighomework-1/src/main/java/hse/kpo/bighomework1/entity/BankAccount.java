package hse.kpo.bighomework1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BankAccount {
    Integer id;
    String name;
    Integer balance; //Копейки
    public BankAccount() {}
    public BankAccount(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
