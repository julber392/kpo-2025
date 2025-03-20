package hse.kpo.bighomework1.entity.things;

import hse.kpo.bighomework1.entity.interfaces.IInventory;
import org.springframework.stereotype.Component;

@Component
public class Thing implements IInventory {
    private int num=0;

    public int getNumber() {
        return num;
    }

    public void setNumber(int Number){
        num=Number;
    }
    public String toString() {
        return "В зоопарк принято на инвентаризацию Thing: inventory_id#"+num;
    }
}
