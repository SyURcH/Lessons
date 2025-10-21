import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.filters;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";

        // Включаем детальное логирование всех запросов и ответов
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // Альтернативный вариант - логирование только при ошибках
        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}