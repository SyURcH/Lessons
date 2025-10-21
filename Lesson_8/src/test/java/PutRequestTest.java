import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PutRequestTest extends BaseTest {

    @Test
    public void testPutRequest() {
        String requestBody = "{" +
                "\"id\": 1," +
                "\"title\": \"Updated Title\"," +
                "\"content\": \"This content has been updated via PUT\"," +
                "\"tags\": [\"api\", \"testing\", \"rest\"]" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("json.id", equalTo(1))
                .body("json.title", equalTo("Updated Title"))
                .body("json.tags", hasItems("api", "testing", "rest"));
    }
}