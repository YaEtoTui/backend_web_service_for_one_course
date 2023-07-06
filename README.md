# backend_web_service_for_one_course
Бэкенд для веб-сайта, который помогает облегчить поиск кабинета в ИРИТ-РТФ. 

http://localhost:8080

| API | Описание | Текст ответа |
| --- | -------- | ------------ |
| GET /api/campus/{nameCampus} | Получение инфы по Кампусу | CampusResponse |
| GET /api/campus/cabinets/cabinet?numberCabinet=р-1 | Поиск номера кабинета | CabinetResponse |

Пока nameCampus есть только "ИРИТ-РТФ"

БД -> PostgreSQL:
  name: web_service_one_course
  username: root
  password: 123

Главное перед тем как включать приложение, создать бд с таким именем что выше, с пользователем и паролем.(DBeaver, например)

Можно поменять тут:

![изображение](https://github.com/YaEtoTui/backend_web_service_for_one_course/assets/102538132/d9e2a15c-e437-47a8-ab92-389bfc91c4f7)


# CampusResponse

```py
{
    "campusID": 1,
    "campusName": "ИРИТ-РТФ",
    "address": {
        "x": 56.840824,
        "y": 60.65076
    },
    "description": "Вход в кампус со стороны улицы Мира 32. Необходим студенческий и пропуск",
    "cabinets": [
        {
            "number": 1,
            "vectors": [
                {
                    "x": 1004.0,
                    "y": 2000.0
                },
                {
                    "x": 1004.0,
                    "y": 1828.0
                },
                {
                    "x": 1544.0,
                    "y": 1828.0
                },
                {
                    "x": 1650.0,
                    "y": 1828.0
                },
                {
                    "x": 1650.0,
                    "y": 1398.0
                },
                {
                    "x": 1774.0,
                    "y": 1398.0
                }
            ]
        },
        {
            "number": 2,
            "vectors": [
                {
                    "x": 1004.0,
                    "y": 2000.0
                },
                {
                    "x": 1004.0,
                    "y": 1828.0
                },
                {
                    "x": 1544.0,
                    "y": 1828.0
                },
                {
                    "x": 1650.0,
                    "y": 1828.0
                },
                {
                    "x": 1650.0,
                    "y": 1190.0
                },
                {
                    "x": 1774.0,
                    "y": 1190.0
                }
            ]
        }
    ]
}
```

# CabinetResponse

```py
{
    "number": 1,
    "vectors": [
        {
            "x": 1004.0,
            "y": 2000.0
        },
        {
            "x": 1004.0,
            "y": 1828.0
        },
        {
            "x": 1544.0,
            "y": 1828.0
        },
        {
            "x": 1650.0,
            "y": 1828.0
        },
        {
            "x": 1650.0,
            "y": 1398.0
        },
        {
            "x": 1774.0,
            "y": 1398.0
        }
    ]
}
```
