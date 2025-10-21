import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PatchRequestTest extends BaseTest {

    @Test
    public void testPatchRequest() {
        String requestBody = "{" +
                "\"status\": \"active\"," +
                "\"last_updated\": \"2024-01-15\"," +
                "\"changes\": [\"fixed bug\", \"updated docs\"]" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("json.status", equalTo("active"))
                .body("json.changes", hasItems("fixed bug", "updated docs"));
    }
}