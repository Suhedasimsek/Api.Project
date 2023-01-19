package Day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task1 {
    /*

    TASK
    Given accept type json
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    Verify the first id is 1
    Verify the first name is aFm
    Verify the last id is 102
    Verify the last name is GHAN
     */

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test

    public void Test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)//İLK 50Yİ GETİR
                .queryParam("page", 1)
                .when().log().all()
                .get("/allusers/alluser");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        int firstid=response.path("id[0]");
        int lastid=response.path("id[-1]"); //49DA OLUR İLK 50Yİ GETİR DEDİĞİM İÇİN
        String firstname=response.path("name[0]");
        String lastname=response.path("name[-1]");//-2 SONDAN BİR ÖNCEYİ GETİRİR


        Assert.assertEquals(firstid,1);
        Assert.assertEquals(lastid,102);
        Assert.assertEquals(firstname,"aFm");
        Assert.assertEquals(lastname,"GHAN");
    }
}
