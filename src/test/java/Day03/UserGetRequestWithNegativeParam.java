package Day03;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserGetRequestWithNegativeParam {


    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }
        /*Given accept type is json
        And path param id is 444
        When user sends a get request to "/allusers/getbyid/{id}
        Then status code is 404
        And content-type is "application/json; charset=UTF-8"
        And "No User Record Found..." message should be in response payload

     */
        @Test
        public void test1() {
            Response response=given().accept(ContentType.JSON)
                    .pathParam("id",444)
                    .when().log().all()//request bodynin body dahil ayrıntılarını veriyor
                    .get("/allusers/getbyid/{id}");

            System.out.println("response.statusCode() = " + response.statusCode());
            Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
            Assert.assertTrue(response.body().asString().contains("No User Record Found..."));

        }


    }



