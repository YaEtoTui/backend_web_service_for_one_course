# backend_web_service_for_one_course
Бэкенд для веб-сайта, который помогает облегчить поиск кабинета в ИРИТ-РТФ. 

http://localhost:8080

| API | Описание | Текст ответа |
| --- | -------- | ------------ |
| GET /api/campus/{nameCampus} | Получение инфы по Кампусу | CampusResponse |
| GET /api/campus/cabinets/list?value= | Поиск списка кабинетов по значению | List<CabinetResponse> |
| GET /api/campus/cabinets/cabinet?numberID=Р-243 | Поиск кабинета вместе с кампусом, в котором он находится | CampusResponse |

Пока nameCampus есть только "irit"

БД -> PostgreSQL:
  name: web_service_one_course
  username: root
  password: 123

Главное перед тем как включать приложение, создать бд с таким именем что выше, с пользователем и паролем.(DBeaver, например)

Можно поменять тут:

![изображение](https://github.com/YaEtoTui/backend_web_service_for_one_course/assets/102538132/d9e2a15c-e437-47a8-ab92-389bfc91c4f7)


# CampusResponse

```py
public class CampusResponse {
    String campusID;
    String campusName;
    Vector addressCampus;
    String descriptionCampus;
    List<CabinetInfo> cabinets;

    @Value
    public static class CabinetInfo {
        String numberCabinet;
        Long floor;
        List<Point> vectors;
    }
}
```

# CabinetResponse

```py
public class CabinetResponse {
    Long id;
    String numberCabinet;
}
```
