package hse.kpo.homework1.entity.things;

import hse.kpo.homework1.entity.interfaces.IInventory;
import org.springframework.stereotype.Component;

@Component
public class Table implements IInventory {
    private int num=0;

    public int getNumber() {
        return num;
    }

    public void setNumber(int Number){
        num=Number;
    }
    public String toString() {
        return "В зоопарк принято на инвентаризацию Table: inventory_id#"+num;
    }
}
