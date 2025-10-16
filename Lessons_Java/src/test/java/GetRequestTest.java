import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTest extends BaseTest {
    @Test
    public void testGetRequest() {
        given().when().get("/get").then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/get"));
    }
}