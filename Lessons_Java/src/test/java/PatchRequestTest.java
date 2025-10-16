import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PatchRequestTest extends BaseTest {
    @Test
    public void testPatchRequest() {
        String requestBody = "{\"patched\": true}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().patch("/patch").then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("json.patched", equalTo(true));
    }
}