package hse.kpo.bighomework1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Category {
    Integer id;
    CategoryType type;
    String name;

    public Category(Integer id, CategoryType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
