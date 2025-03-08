package hse.kpo.bighomework1.services.interfaces;

import java.util.List;

public interface IStorage<TParam>{
    List<TParam> getStorage();
    void add(TParam p);
    void delete(int index);
}
