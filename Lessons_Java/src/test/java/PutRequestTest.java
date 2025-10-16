import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PutRequestTest extends BaseTest {
    @Test
    public void testPutRequest() {
        String requestBody = "{\"key\": \"value\", \"updated\": true}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put("/put").then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("json.key", equalTo("value"));
    }
}