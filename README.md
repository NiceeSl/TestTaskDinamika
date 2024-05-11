### :woman_technologist:Этот проект представляет собой REST API для управления книгами.
<div id="header" align="center">
  <img src="https://media.giphy.com/media/M9gbBd9nbDrOTu1Mqx/giphy.gif" width="100"/>
</div>

### :woman_technologist: Требования :
- Java Development Kit (JDK) версии 8 или выше
- Apache Maven
- СУБД (PostgreSQL)

### :woman_technologist: Установка и запуск: 
1. Клонируйте репозиторий на свой компьютер:

    bash:
    git clone https://github.com/NiceeSl/TestTaskDinamika.git
  

2. В корне проекта создайте файл `application.properties` и добавьте следующие настройки для подключения к вашей базе данных:

    properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/db_name
    spring.datasource.username=db_username
    spring.datasource.password=db_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    

    Замените `db_name`, `db_username` и `db_password` на соответствующие значения вашей базы данных.

4. Соберите проект с помощью Maven:

    bash:
    mvn package
    

5. Запустите JAR файл:

    bash:
    java -jar target/your-project.jar
    

После выполнения этих шагов приложение будет запущено и готово к использованию.

## Использование API

API будет доступно по адресу `http://localhost:8080`. Вы можете использовать любой HTTP клиент (например, cURL или Postman) для выполнения запросов к API.

Примеры запросов:

- Получить все книги:

    
    GET http://localhost:8080/books
    

- Получить книгу по ID:

    
    GET http://localhost:8080/books/{id}
    

- Добавить новую книгу:

    
    POST http://localhost:8080/api/books
    Content-Type: application/json

    {
        "title": "Название книги",
        "author": "Автор книги",
    }

  
- Получение списка читателей:

    GET /api/readers


- Взятие книги на прочтение:

    POST /api/borrow/{clientId}/{bookId}
  
Принимает параметры {clientId} и {bookId} и возвращает сообщение об успешном взятии книги на прочтение.


       

