package Day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UserWith_JsonPath {
    /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And user's name should be Thomas Eduson
    And user's id should be 111
    And user's email should be thomas@test.com
   */

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test

    public void Test1() {
        Response response = given().contentType(ContentType.JSON)//given().accept.(ContentType.JSON)
                .pathParams("id", 111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        int idJson=jsonPath.getInt("id[0]"); //String ifadeyi jsonpath sayesinde int alabildik
        String nameJson=jsonPath.getString("name[0]");
        String emailJson=jsonPath.getString("email[0]");

        Assert.assertEquals(idJson,111);
        Assert.assertEquals(nameJson,"Thomas Eduson");
        Assert.assertEquals(emailJson,"thomas@test.com");


    }
}
