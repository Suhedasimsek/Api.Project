package Api_Tests.HttpMethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class POST_NewUser {

    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void NewUser(){

        String body="{\n" +
                "  \"name\": \"New User\",\n" +
                "  \"email\": \"newuser122@krafttechexlab.com\",\n" +
                "  \"password\": \"123467\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .body(body)
                .when().log().all()
                .post("/allusers/register").prettyPeek();

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
        String token=response.path("token");
        System.out.println("token = " + token);


    }
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzA2Iiwic3RhcnQiOjE2NzQ4MzkwMzUsImVuZHMiOjE2NzU0NDM4MzV9.zcs-pLn_kzmqbccvLfiZH8JiWWlk7EhqKGwCfQSv4zxMd52_Euhs9Y4CVgYQVREadxuNWtJ3DVVQnXxyWydDjA
}

