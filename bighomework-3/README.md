# Проект: E-Shop Microservices

## Краткое описание

Данный проект представляет собой микросервисную архитектуру для интернет-магазина, реализованную на языке **Java** с использованием **Spring Boot** и **RabbitMQ** для асинхронного взаимодействия между сервисами.

Проект состоит из двух основных микросервисов:
- `order-service` — управление заказами
- `payment-service` — управление оплатой и счетами пользователей

Сервисы разворачиваются и взаимодействуют между собой через **Docker Compose**, что обеспечивает изоляцию, масштабируемость и удобство запуска.

---

## Структура проекта

```
e-shop-microservices/
├── order-service/
│   ├── src/main/java/com/example/orderservice/
│   │   ├── config/         # Настройки RabbitMQ, сервисов и т.д.
│   │   ├── controller/     # REST API для заказов
│   │   ├── domain/         # Сущности (Order и др.)
│   │   ├── dto/            # DTO-классы
│   │   ├── exception/      # Обработка ошибок
│   │   ├── observer/       # Подписка на события (RabbitMQ)
│   │   ├── repository/     # Доступ к данным (Spring Data)
│   │   ├── service/        # Бизнес-логика заказов
│   │   └── OrderServiceApplication.java
│   └── build.gradle.kts
├── payment-service/
│   ├── src/main/java/com/example/paymentservice/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── domain/
│   │   ├── dto/
│   │   ├── exception/
│   │   ├── observer/
│   │   ├── repository/
│   │   ├── service/
│   │   └── PaymentServiceApplication.java
│   └── build.gradle.kts
├── docker-compose.yml     # Оркестрация запуска всех микросервисов и брокера сообщений
├── settings.gradle.kts
└── README.md
```

---

## Реализованный функционал

### Order Service
- Создание заказов (асинхронно, через RabbitMQ отправляется сообщение об оплате)
- Получение списка заказов пользователя
- Получение статуса отдельного заказа

### Payment Service
- Создание счета для пользователя (только один счет на пользователя)
- Пополнение счета
- Получение текущего баланса пользователя
- Обработка сообщений об оплате из очереди RabbitMQ (с гарантией `at most once`)

---

## Использованные технологии

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **RabbitMQ** (через spring-amqp)
- **PostgreSQL**
- **Docker и Docker Compose**
- **Gradle Kotlin DSL**

---


# Инструкция по запуску и проверке микросервисов

## 1. Запуск через Docker Compose

```bash
docker-compose up --build
```

Это запустит:

- PostgreSQL
- RabbitMQ
- Order Service (на порту `8080`)
- Payment Service (на порту `8081`)

---

## 2. Проверка работы сервисов

Откройте новые терминалы и проверьте:

### Order Service (порт `8080`):

```bash
curl http://localhost:8080/api/orders/health
```

Ожидаемый ответ:

```json
{"status":"UP"}
```

### Payment Service (порт `8081`):

```bash
curl http://localhost:8081/api/payments/health
```

---

## 3. RabbitMQ Management Console

Откройте в браузере:

```
http://localhost:15672
```

- Логин: `guest`
- Пароль: `guest`

---

## 4. Создание тестовых данных

### Создайте аккаунт:

```bash
curl -X POST "http://localhost:8081/api/payments/account?userId=1"
```

### Пополните баланс:

```bash
curl -X POST "http://localhost:8081/api/payments/deposit" \
-H "Content-Type: application/json" \
-d '{"userId":1,"amount":1000}'
```

### Создайте заказ:

```bash
curl -X POST "http://localhost:8080/api/orders" \
-H "Content-Type: application/json" \
-d '{"userId":1,"amount":100,"description":"Test Order"}'
```

---

## 5. Просмотр данных

### Проверьте баланс:

```bash
curl http://localhost:8081/api/payments/balance/1
```

### Получите список заказов:

```bash
curl http://localhost:8080/api/orders/user/1
```

---

## 6. Остановка проекта

Когда закончите работу:

```bash
docker-compose down
```
