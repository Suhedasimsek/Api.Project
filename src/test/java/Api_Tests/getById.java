package Api_Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getById {
    /*
   TASK
        When user sends a GET request to /allusers/getbyid/111
        Then Status code should be 200
        And content type should be application/json; charset=UTF-8
        And json body should contain Thomas Eduson

    */


        String exlabUrl = "https://www.krafttechexlab.com/sw/api/v1";

        @Test //Bütün kullanıcıları getir
        public void Test1() {
            Response response =  RestAssured.given()
                    .accept(ContentType.JSON)
                    .pathParams("id",111)
                    .and()
                    .when()
                    .get(exlabUrl+"/allusers/getbyid/{id}");
            System.out.println("response.statusCode() = " + response.statusCode());
            Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
            //2. yol
            Assert.assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");
            //3.YOL
            Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");
            Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));//çok sağlıklı değil. Başka keye ait value da gösterebilir
            Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
            Assert.assertEquals(response.header("content-Length"), "636");
        }
}
