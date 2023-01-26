package Api_Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class queryParams {
    /*

   TASK
   Given accept type json
   And query  parameter value name Thomas Eduson
   And query  parameter value skills Cypress
   And query  parameter value pagesize 50
   And query  parameter value page 1
   When user sends GET request to /allusers/alluser
   Then response status code should be 200
   And response content-type application/json; charset=UTF-8
   And response body contains "Developer" message

    */
    @Test

    public void queryParam() {
        Response response =  given().accept(ContentType.JSON)
                .queryParam("name","Thomas Eduson")
                .queryParam("skills","Cypress")
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().log().all()
                .get("/allusers/alluser");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Developer"));
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        Assert.assertTrue(response.body().asString().contains("Cypress"));

    }
}
