package hse.kpo.bighomework1.entity;

public enum CategoryType {

    INCOME("ДОХОД"), OUTCOME("РАСХОД");
    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
