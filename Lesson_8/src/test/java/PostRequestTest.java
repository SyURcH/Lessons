import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRequestTest extends BaseTest {

    @Test
    public void testPostWithJsonBody() {
        String requestBody = "{" +
                "\"key\": \"value\"," +
                "\"number\": 123," +
                "\"boolean\": true," +
                "\"nested\": {" +
                "    \"field\": \"nestedValue\"" +
                "}" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("json.key", equalTo("value"))
                .body("json.number", equalTo(123))
                .body("json.boolean", equalTo(true))
                .body("json.nested.field", equalTo("nestedValue"))
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPostWithTextPlain() {
        String textBody = "This is a plain text message for testing API";

        given()
                .log().all()
                .contentType(ContentType.TEXT)
                .body(textBody)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("data", equalTo(textBody))
                .body("headers.'content-type'", containsString("text/plain"));
    }

    @Test
    public void testPostWithFormData() {
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("username", "testuser")
                .formParam("password", "testpass")
                .formParam("remember", "true")
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("form.username", equalTo("testuser"))
                .body("form.password", equalTo("testpass"))
                .body("form.remember", equalTo("true"));
    }

    @Test
    public void testPostWithXmlBody() {
        String xmlBody = "<user>" +
                "<id>123</id>" +
                "<name>John Doe</name>" +
                "<email>john@example.com</email>" +
                "</user>";

        given()
                .log().all()
                .contentType(ContentType.XML)
                .body(xmlBody)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                .body("data", containsString("<user>"))
                .body("data", containsString("John Doe"));
    }