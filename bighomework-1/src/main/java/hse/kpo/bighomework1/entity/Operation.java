package hse.kpo.bighomework1.entity;

import hse.kpo.bighomework1.data.ReportFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Operation implements Exportable{
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
        data.put("category", category.getId().toString());
        data.put("bankAccount",bankAccount.getId().toString());
        data.put("amount",amount.toString());
        data.put("date",date);
        data.put("descrtiption",description);
        data.put("type", type.toString());
        return data;
    }
    private Map<String, String> dataYaml(){
        return dataJson();
    }
    private Map<String, String> dataCSV(){
        return dataJson();
    }
}
