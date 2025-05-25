Запустите систему командой: docker-compose up --build


### После запуска системы API будет доступно по адресу http://localhost:8080/api/files. Основные эндпоинты:

# Загрузка файла:
POST /api/files/upload
Form-data: file=[текстовый файл]
Получение содержимого файла:
GET /api/files/{fileId}/content
Анализ файла:
POST /api/files/{fileId}/analyze
Получение результатов анализа:
GET /api/files/{fileId}/analysis 
# Проверка работы

Вы можете использовать Postman или curl для тестирования API:

Загрузить файл:
bash
curl -X POST -F "file=@example.txt" http://localhost:8080/api/files/upload
Получить анализ:
bash
curl -X POST http://localhost:8080/api/files/1/analyze
Получить результаты анализа:
bash
curl http://localhost:8080/api/files/1/analysis

# Text Scanner System - Документация
## Архитектура системы

Система состоит из 3 микросервисов:

API Gateway - входная точка для всех запросов, маршрутизирует запросы к соответствующим сервисам
File Storage Service - отвечает за хранение и управление текстовыми файлами
File Analysis Service - выполняет анализ текстовых файлов (подсчет абзацев, слов, символов)

# Спецификация API

Базовый URL

http://localhost:8080/api/files

## Эндпоинты

Метод	Путь	Описание

GET	/	Проверка работоспособности сервиса

POST	/upload	Загрузка текстового файла

GET	/{fileId}/content	Получение содержимого файла

POST	/{fileId}/analyze	Запуск анализа файла

GET	/{fileId}/analysis	Получение результатов анализа