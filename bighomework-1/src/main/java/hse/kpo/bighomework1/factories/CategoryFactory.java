package hse.kpo.bighomework1.factories;

import hse.kpo.bighomework1.entity.Category;

public class CategoryFactory {
    int id=0;
    Category createCategory(int id, String type, String name){
        return new Category(id++,type,name);
    }
}
