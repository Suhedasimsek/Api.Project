package Api_Tests.HttpMethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostNewUser {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void newUser() {


        Response response = given().accept(ContentType.JSON)
                .when().log().all()
                .post("/allusers/register").prettyPeek();

        System.out.println("response.prettyPrint() = " + response.prettyPrint());


    }
}
