package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BankAccount implements Exportable {
    Integer id;
    String name;
    Integer balance; //Копейки
    public BankAccount() {}
    public BankAccount(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public Map<String, String> export(ReportFormat format) throws IllegalArgumentException{
        return switch (format){
            case CSV -> dataCSV();
            case JSON -> dataJson();
            case YAML -> dataYaml();
            default -> throw new IllegalArgumentException("Не поддерживает export в данном формате");
        };
    }
    private Map<String, String> dataJson(){
        Map<String,String> data=new HashMap<>();
        data.put("id",id.toString());
        data.put("name",name);
        data.put("balance",balance.toString());
        return data;
    }
    private Map<String, String> dataYaml(){
        return dataJson();
    }
    private Map<String, String> dataCSV(){
        return dataJson();
    }
}
