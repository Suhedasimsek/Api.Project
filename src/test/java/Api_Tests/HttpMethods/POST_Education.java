package Api_Tests.HttpMethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class POST_Education {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {
        POSTNewUserInfo newUserInfo = new POSTNewUserInfo("Seyda3",
                "seyda3@krafttechexlab.com", "seyda3", "About Me", "5");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        String token=response.path("token");
        String educationBody= "{\n" +
                "  \"school\": \"Sakarya\",\n" +
                "  \"degree\": \"Master\",\n" +
                "  \"study\": \"SDET\",\n" +
                "  \"fromdate\": \"2020-01-01\",\n" +
                "  \"todate\": \"2020-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"SDET\"\n" +
                "}";
        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody) //serialization
                .queryParam("token",token)
                .when()
                .post("/education/add");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
    }
}
