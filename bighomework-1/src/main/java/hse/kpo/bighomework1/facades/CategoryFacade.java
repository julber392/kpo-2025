package hse.kpo.bighomework1.facades;

import hse.kpo.bighomework1.entity.BankAccount;
import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.factories.BankAccountFactory;
import hse.kpo.bighomework1.factories.CategoryFactory;
import hse.kpo.bighomework1.services.BankAccountStorage;
import hse.kpo.bighomework1.services.CategoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoryFacade {
    final CategoryStorage categoryStorage;
    final CategoryFactory categoryFactory;
    @Autowired
    public CategoryFacade(CategoryStorage categoryStorage, CategoryFactory categoryFactory) {
        this.categoryStorage = categoryStorage;
        this.categoryFactory = categoryFactory;
    }

    public void create(CategoryType type, String name){
        categoryStorage.add(categoryFactory.createCategory(type,name));
    }
    public CategoryStorage get(){
        return categoryStorage;
    }
    public void delete(int id){
        categoryStorage.delete(id);
    }
    public void update(Integer id, CategoryType newType, String newName){
        categoryStorage.add(categoryFactory.createCategory(id,newType,newName));
    }
}
