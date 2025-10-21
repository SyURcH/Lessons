import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest extends BaseTest {

    @Test
    public void testDeleteRequest() {
        given()
                .log().all()
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/delete"))
                .body("headers", notNullValue());
    }

    @Test
    public void testDeleteWithBody() {
        String requestBody = "{" +
                "\"resource_id\": 456," +
                "\"reason\": \"no longer needed\"," +
                "\"deleted_by\": \"admin\"" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .statusCode(200)
                .body("json.resource_id", equalTo(456))
                .body("json.reason", equalTo("no longer needed"));
    }
}