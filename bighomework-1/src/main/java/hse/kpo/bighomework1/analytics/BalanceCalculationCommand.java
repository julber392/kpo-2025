package hse.kpo.bighomework1.analytics;

import hse.kpo.bighomework1.entity.CategoryType;
import hse.kpo.bighomework1.entity.Operation;
import hse.kpo.bighomework1.services.OperationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BalanceCalculationCommand implements Command {
    private final Map<Integer,Operation> operations;
    @Autowired
    public BalanceCalculationCommand(OperationStorage operationStorage) {
        this.operations = operationStorage.getStorage();
    }

    @Override
    public void execute() {
        Map<Integer, Integer> balanceByAccount = new HashMap<>();

        operations.values().forEach(op -> {
            int sign = CategoryType.INCOME.equals(op.getType()) ? 1 : -1;
            balanceByAccount.merge(op.getBankAccount().getId(), sign * op.getAmount(), Integer::sum);
        });

        balanceByAccount.forEach((accountId, balance) ->
                System.out.println("Счет ID " + accountId + ": Разница доходов и расходов = " + balance));
    }
}
