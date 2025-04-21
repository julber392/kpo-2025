# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
Порождающие паттерны
Порождающие (Creational) паттерны проектирования помогают создавать объекты удобным и гибким способом.

К ним относятся:

Singleton (Одиночка)
Factory (Фабрика)
Factory Method (Фабричный метод)
Abstract Factory (Абстрактная фабрика)
Builder (Строитель)
Prototype (Прототип)
Singleton (Одиночка)
Гарантирует, что у класса есть только один экземпляр и предоставляет к нему глобальную точку доступа.

class Singleton {
private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
Factory (Фабрика)
Создаёт объекты без указания точного класса.

class Product {}

class ConcreteProduct extends Product {}

class Factory {
public static Product createProduct() {
return new ConcreteProduct();
}
}
Factory Method (Фабричный метод)
Определяет интерфейс для создания объектов, но оставляет подклассам выбор типа создаваемого объекта.

abstract class Creator {
abstract Product factoryMethod();
}

class ConcreteCreator extends Creator {
@Override
Product factoryMethod() {
return new ConcreteProduct();
}
}
Abstract Factory (Абстрактная фабрика)
Предоставляет интерфейс для создания семейств взаимосвязанных объектов.


interface Button {
void render();
}

class WindowsButton implements Button {
public void render() { System.out.println("Windows Button"); }
}

class MacOSButton implements Button {
public void render() { System.out.println("MacOS Button"); }
}

interface GUIFactory {
Button createButton();
}

class WindowsFactory implements GUIFactory {
public Button createButton() { return new WindowsButton(); }
}

class MacOSFactory implements GUIFactory {
public Button createButton() { return new MacOSButton(); }
}
Builder (Строитель)
Позволяет создавать сложные объекты пошагово.

class Product {
private String part1;
private String part2;

    public static class Builder {
        private String part1;
        private String part2;

        public Builder setPart1(String part1) {
            this.part1 = part1;
            return this;
        }

        public Builder setPart2(String part2) {
            this.part2 = part2;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.part1 = this.part1;
            product.part2 = this.part2;
            return product;
        }
    }
}
Prototype (Прототип)
Создаёт новые объекты, копируя существующие.


class Prototype implements Cloneable {
String data;

    public Prototype(String data) {
        this.data = data;
    }

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }
}

Уникальный факт
Паттерн Singleton считается антипаттерном в многопоточных приложениях, если не используется потокобезопасная реализация (например, volatile или enum).







