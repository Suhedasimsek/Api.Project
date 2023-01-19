package Day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task3 {
    /*
    TASK
    Given accept type is json
    When user sends a GET request to /allusers/alluser
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And second name should be isa akyuz
    And first user's experience jobs should be" Junior Developer1, Junior Developer1, Junior Developer"
     */

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test

    public void Test1() {
        Response response = given().contentType(ContentType.JSON)//given().accept.(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().log().all()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");


        JsonPath jsonPath=response.jsonPath();
        String secondname=jsonPath.getString("name[1]");
        Assert.assertEquals(secondname,"isa akyuz");

        List<String> jobs=jsonPath.getList("experience.job[0]");
        Assert.assertEquals(jobs,"[Junior Developer1, Junior Developer1, Junior Developer]");




    }
}
