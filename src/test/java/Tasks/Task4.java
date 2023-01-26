package Tasks;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task4 {
    /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    Get user skills
    And user's first skill should be PHP

   */

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test

    public void Test1() {
        Response response = given().contentType(ContentType.JSON)//given().accept.(ContentType.JSON)
                .pathParam("id",111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        System.out.println("response.body().path(\"skills\") = " + response.body().path("skills"));

//PATH methodu liste olarak String assign etmeye izin vermiyor.Ama jsonpath veriyor
        JsonPath jsonPath=response.jsonPath();
       String skills=jsonPath.getString("skills");
       String firstSkill=jsonPath.getString("skills[0][0]");//ARAY İÇİNDE ARAY DİMENTİAL ARAY
       Assert.assertEquals(firstSkill,"PHP");

       //2.yol
        List<String> skills2=jsonPath.getList("skills[0]");
        Assert.assertEquals(skills2.get(0), "PHP");
    }
}
