# backend_web_service_for_one_course
Бэкенд для веб-сайта, который помогает облегчить поиск кабинета в ИРИТ-РТФ. 

http://localhost:8080

| API | Описание | Текст ответа |
| --- | -------- | ------------ |
| GET /api/campus/{nameCampus} | Получение инфы по Кампусу | CampusResponse |

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

```
