package Api_Tests.HttpMethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class DeleteExperince {
    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void deleteExperience(){

        Response response= given().accept(ContentType.JSON)
                .pathParam("id",250)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzA2Iiwic3RhcnQiOjE2NzQ4MzkwMzUsImVuZHMiOjE2NzU0NDM4MzV9.zcs-pLn_kzmqbccvLfiZH8JiWWlk7EhqKGwCfQSv4zxMd52_Euhs9Y4CVgYQVREadxuNWtJ3DVVQnXxyWydDjA")
                .when().log().all()
                .delete("/experience/delete/{id}").prettyPeek();




    }

}
