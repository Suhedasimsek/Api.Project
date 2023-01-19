package Day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task2 {

    /*
    Given accept type json
    When user sends a get request to https://demoqa.com/BookStore/v1/Books
    Then status code should be 200
    And content typr should be application/json; charset=utf-8
    And the first book isbn should be 9781449325862
    And the first book publisher should be O'Reilly Media

     */
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://demoqa.com/BookStore/v1";
    }

    @Test

    public void Test1() {
        Response response = given().accept(ContentType.JSON)
                .when().log().all()
                .get("/Books");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        String firstisbn = response.body().path("books.isbn[0]");//parenta gidip çağırdık
        Assert.assertEquals(firstisbn, "9781449325862");

        String firstBookPub = response.body().path("books.publisher[0]");
        Assert.assertEquals(firstBookPub, "O'Reilly Media");


    }
}
