package hse.kpo.bighomework1.analytics;

import hse.kpo.bighomework1.entity.Category;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.services.CategoryStorage;
import hse.kpo.bighomework1.services.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class GroupByCategoryCommand implements Command {
    private final Map<Integer,Operation> operations;
    private final Map<Integer,Category> categories;

    public GroupByCategoryCommand(OperationStorage operations, CategoryStorage categories) {
        this.operations = operations.getStorage();
        this.categories = categories.getStorage();
    }
    public void execute() {
        Map<String, Integer> grouped = operations.values().stream()
                .collect(Collectors.groupingBy(
                        op -> categories.get(op.getCategory().getId()).getName(),
                        Collectors.summingInt(Operation::getAmount)
                ));

        // Выводим результат группировки
        System.out.println("Группировка по категориям: " + grouped);
    }
}
