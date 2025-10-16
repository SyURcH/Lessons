import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRequestTest extends BaseTest {
    @Test
    public void testPostRequest() {
        String requestBody = "{\"key\": \"value\"}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/post").then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("json.key", equalTo("value"));
    }
}