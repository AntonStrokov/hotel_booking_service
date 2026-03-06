# Hotel Booking Service

Модуль для бронирования отелей на основе Spring Boot

## Описание проекта

Hotel Booking Service - это микросервис для управления бронированиями отелей, построенный на Spring Boot. Сервис предоставляет REST API для работы с отелями, номерами, бронированиями и пользователями.

## Архитектура и технологии

- **Фреймворк**: Spring Boot 3.x
- **База данных**: MongoDB
- **Message Broker**: Apache Kafka
- **Аутентификация**: Spring Security
- **Логирование**: Аспектно-ориентированное программирование (AOP)
- **Контейнеризация**: Docker
- **Сборка**: Maven

## Структура проекта

```
src/main/java/com/example/hotelbooking/hotel_booking_service/
├── controller/           # REST контроллеры
│   ├── BookingController.java
│   ├── HotelController.java
│   ├── RoomController.java
│   ├── UserController.java
│   └── StatisticsController.java
├── service/              # Бизнес-логика
│   ├── impl/             # Реализации сервисов
│   ├── kafka/            # Kafka продюсеры и консьюмеры
│   └── *.java            # Интерфейсы сервисов
├── repository/           # Доступ к данным
│   ├── specifications/    # JPA спецификации
│   └── *.java           # Репозитории
├── model/                # Сущности базы данных
├── dto/                  # Data Transfer Objects
│   ├── user/
│   ├── hotel/
│   ├── room/
│   ├── booking/
│   └── statistics/
├── mapper/               # Мапперы для преобразования DTO/Entity
├── config/               # Конфигурации Spring
├── exception/            # Обработка исключений
├── security/             # Настройки безопасности
├── aspect/               # Аспекты для логирования
└── HotelBookingServiceApplication.java
```

## Основные функции

### Управление отелями
- Создание, чтение, обновление, удаление отелей
- Поиск отелей по различным критериям
- Управление информацией об отелях

### Управление номерами
- CRUD операции для номеров
- Поиск доступных номеров
- Управление ценами и характеристиками номеров

### Бронирование
- Создание бронирований
- Управление статусами бронирований
- Поиск и фильтрация бронирований

### Пользователи
- Регистрация и аутентификация пользователей
- Управление профилями
- Ролевая модель доступа

### Статистика
- Сбор статистики по бронированиям
- Аналитика пользовательской активности
- Отчеты через Kafka события

## Запуск проекта

### Предварительные требования
- Java 17+
- Maven 3.6+
- Docker и Docker Compose
- MongoDB
- Apache Kafka

### Локальный запуск

1. Клонируйте репозиторий:
```bash
git clone <repository-url>
cd hotel-booking-service
```

2. Запустите инфраструктуру через Docker Compose:
```bash
docker-compose up -d
```

3. Соберите и запустите приложение:
```bash
mvn clean install
mvn spring-boot:run
```

### Настройка окружения

Создайте файл `application.yml` в `src/main/resources/` с настройками:

```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/hotel-booking
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: hotel-booking-group
```

## API Endpoints

### Отели
- `GET /api/hotels` - Получить список отелей
- `POST /api/hotels` - Создать отель
- `GET /api/hotels/{id}` - Получить отель по ID
- `PUT /api/hotels/{id}` - Обновить отель
- `DELETE /api/hotels/{id}` - Удалить отель

### Номера
- `GET /api/rooms` - Получить список номеров
- `POST /api/rooms` - Создать номер
- `GET /api/rooms/{id}` - Получить номер по ID
- `PUT /api/rooms/{id}` - Обновить номер
- `DELETE /api/rooms/{id}` - Удалить номер

### Бронирования
- `GET /api/bookings` - Получить список бронирований
- `POST /api/bookings` - Создать бронирование
- `GET /api/bookings/{id}` - Получить бронирование по ID
- `PUT /api/bookings/{id}` - Обновить бронирование
- `DELETE /api/bookings/{id}` - Отменить бронирование

### Пользователи
- `GET /api/users` - Получить список пользователей
- `POST /api/users` - Зарегистрировать пользователя
- `GET /api/users/{id}` - Получить пользователя по ID
- `PUT /api/users/{id}` - Обновить пользователя

## Kafka события

Сервис генерирует следующие события:

### Booking Events
- `BOOKING_CREATED` - создание бронирования
- `BOOKING_UPDATED` - обновление бронирования
- `BOOKING_CANCELLED` - отмена бронирования

### User Events
- `USER_REGISTERED` - регистрация нового пользователя
- `USER_UPDATED` - обновление профиля пользователя

## Структура данных

### Основные сущности
- **Hotel** - информация об отеле
- **Room** - номер в отеле
- **Booking** - бронирование
- **User** - пользователь системы
- **UserActionLog** - лог действий пользователя

## Безопасность

- Аутентификация через Spring Security
- Ролевая модель доступа (ROLE_USER, ROLE_ADMIN)
- Хэширование паролей
- Защита от CSRF атак

## Логирование

Приложение использует аспектно-ориентированное программирование для логирования:
- Вход/выход из методов сервисов
- Параметры вызовов
- Время выполнения операций

## Мониторинг и метрики

Сервис предоставляет эндпоинты для мониторинга:
- `/actuator/health` - состояние сервиса
- Логирование действий пользователей
- Статистика бронирований

## Разработка

### Запуск тестов
```bash
mvn test
```

### Форматирование кода
```bash
mvn spotless:apply
```

### Проверка зависимостей
```bash
mvn dependency:analyze
```

## Конфигурация Docker

`Dockerfile` и `docker-compose.yml` содержат настройки для контейнеризации приложения и зависимых сервисов (MongoDB, Kafka).

## Контакты

Для вопросов и предложений обращайтесь к команде разработки.

---
*Проект находится в активной разработке*