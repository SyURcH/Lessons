import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTest extends BaseTest {

    @Test
    public void testGetWithQueryParameters() {
        given()
                .log().all()
                .queryParam("foo", "bar")
                .queryParam("hello", "world")
                .queryParam("number", 123)
                .when()
                .get("/get")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/get"))
                .body("args.foo", equalTo("bar"))
                .body("args.hello", equalTo("world"))
                .body("args.number", equalTo("123"))
                .body("headers", notNullValue());
    }
    @Test
    public void testGetWithSpecialCharacters() {
        given()
                .log().all()
                .queryParam("search", "test data")
                .queryParam("user", "john@example.com")
                .queryParam("page", 1)
                .when()
                .get("/get")
                .then()
                .log().all()
                .statusCode(200)
                .body("args.search", equalTo("test data"))
                .body("args.user", equalTo("john@example.com"))
                .body("args.page", equalTo("1"));
    }
}