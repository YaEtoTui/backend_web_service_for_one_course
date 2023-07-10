# backend_web_service_for_one_course
Бэкенд для веб-сайта, который помогает облегчить поиск кабинета в ИРИТ-РТФ. 

http://localhost:8080

| API | Описание | Текст ответа |
| --- | -------- | ------------ |
| GET /api/campus/cabinets/list?value= | Поиск списка кабинетов по значению | List of CabinetResponse |
| GET /api/campus/cabinets/cabinet?numberID= | Поиск кабинета вместе с кампусом, в котором он находится | CampusAndCabinetResponse |

Пока nameCampus есть только "irit"

БД -> PostgreSQL:
  name: web_service_one_course
  username: root
  password: 123

Главное перед тем как включать приложение, создать бд с таким именем что выше, с пользователем и паролем.(DBeaver, например)

Можно поменять тут:

![изображение](https://github.com/YaEtoTui/backend_web_service_for_one_course/assets/102538132/d9e2a15c-e437-47a8-ab92-389bfc91c4f7)


# CampusAndCabinetResponse

```py
@Value
public class CampusAndCabinetResponse {
    String campusID;
    String campusName;
    Vector addressCampus;
    String descriptionCampus;
    CabinetInfo cabinet;

    @Value
    public static class CabinetInfo {
        String numberCabinet;
        String descriptionCabinet;
        List<String> steps;
        List<FloorsInfo> floors;

        @Value
        public static class FloorsInfo {
            Integer floor;
            List<Point> vectors;
        }

    }
}
```

# CabinetResponse

```py
@Value
public class CabinetResponse {
    Long id;
    String numberCabinet;

```
