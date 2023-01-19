package Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class queryParamswithMap {
    @Test

    public void requestwithMap() {
        Map<String,Object> mapBody=new HashMap<>();
        mapBody.put("name","Thomas Eduson");
        mapBody.put("skills","Cypress");
        mapBody.put("pagesize",50);
        mapBody.put("page",1);
        Response response =  given().accept(ContentType.JSON)
                .queryParams(mapBody)
                .when().log().all()
                .get("/allusers/alluser");
        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Developer"));
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        Assert.assertTrue(response.body().asString().contains("Cypress"));


    }
}
