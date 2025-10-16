import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest extends BaseTest {
    @Test
    public void testDeleteRequest() {
        given().when().delete("/delete").then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}