## Реализованный функционал:

### Доменная модель (Domain Layer):
Классы Animal, Enclosure, FeedingSchedule с инкапсулированной бизнес-логикой

Value Objects: AnimalSpecies, AnimalName, FoodType, EnclosureType

Доменные события: AnimalMovedEvent, FeedingTimeEvent

### Сервисы приложения (Application Layer):

AnimalTransferService - для перемещения животных между вольерами

FeedingOrganizationService - для организации кормления

ZooStatisticsService - для сбора статистики

### Репозитории: 
AnimalRepository, EnclosureRepository, FeedingScheduleRepository

### Контроллеры (Presentation Layer):
AnimalController - CRUD для животных + перемещение

EnclosureController - CRUD для вольеров

FeedingScheduleController - управление расписанием кормлений

StatisticsController - получение статистики

### Infrastructure Layer:

In-memory реализации репозиториев

### Поддержка всех use cases:
a. Добавление/удаление животных

b. Добавление/удаление вольеров

c. Перемещение животных

d. Просмотр расписания кормлений

e. Добавление новых кормлений

f. Просмотр статистики

### Примененные концепции:

Domain-Driven Design:

Богатая доменная модель с инкапсуляцией бизнес-правил (напр., проверки при перемещении животных)

Value Objects для примитивных типов

Доменные события для реакции на изменения

Агрегаты (Animal, Enclosure как корни агрегатов)

### Clean Architecture:

Четкое разделение на слои: Domain, Application, Infrastructure, Presentation

Domain слой не зависит от других слоев

Зависимости между слоями через интерфейсы

Бизнес-логика полностью в Domain и Application слоях

Инфраструктурные детали (хранилище) вынесены в отдельный слой

### Дополнительные принципы:

Dependency Injection

SOLID принципы

CQRS (разделение команд и запросов)

### Покрытие тестами более 65 процентов
![photo1.png](photo%2Fphoto1.png)