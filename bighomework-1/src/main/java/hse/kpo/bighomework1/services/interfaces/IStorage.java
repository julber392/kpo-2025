package hse.kpo.bighomework1.services.interfaces;

import java.util.List;
import java.util.Map;

public interface IStorage<TParam>{
    Map<Integer,TParam> getStorage();
    void add(TParam p);
    void delete(int index);
}
